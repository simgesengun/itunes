package com.simgesengun.itunes.network

import com.simgesengun.itunes.network.model.NetworkErrorType.EMPTY_RESPONSE_ERROR
import com.simgesengun.itunes.network.model.NetworkErrorType.NETWORK_ERROR
import com.simgesengun.itunes.network.model.NetworkErrorType.TIMEOUT_ERROR
import com.simgesengun.itunes.network.model.NetworkErrorType.UNKNOWN_ERROR
import com.simgesengun.itunes.network.model.NetworkErrorType.UNKNOWN_HOST_ERROR
import com.simgesengun.itunes.network.model.NetworkResponse
import java.io.IOException
import java.net.SocketTimeoutException
import java.net.UnknownHostException
import okhttp3.Request
import okhttp3.ResponseBody
import okio.Timeout
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Converter
import retrofit2.Response

internal class NetworkResponseCall<S : Any, E : Any>(
    private val delegate: Call<S>,
    private val errorConverter: Converter<ResponseBody, E>
) : Call<NetworkResponse<S, E>> {

    override fun enqueue(callback: Callback<NetworkResponse<S, E>>) {
        return delegate.enqueue(object : Callback<S> {
            override fun onResponse(call: Call<S>, response: Response<S>) {
                val body = response.body()
                val code = response.code()
                val error = response.errorBody()
                when {
                    response.isSuccessful -> {
                        if (body != null) {
                            NetworkResponse.Success(body)
                        } else {
                            NetworkResponse.NetworkError(EMPTY_RESPONSE_ERROR)
                        }
                    }
                    else -> {
                        val errorBody = error?.let {
                            runCatching {
                                errorConverter.convert(error)
                            }.getOrNull()
                        }
                        errorBody?.let {
                            NetworkResponse.ApiError(it, code)
                        } ?: NetworkResponse.NetworkError(EMPTY_RESPONSE_ERROR)
                    }
                }.runCatching {
                    callback.invoke(this)
                }.getOrElse {
                    NetworkResponse.NetworkError(UNKNOWN_ERROR)
                }
            }

            override fun onFailure(call: Call<S>, throwable: Throwable) {
                when (throwable) {
                    is SocketTimeoutException -> TIMEOUT_ERROR
                    is UnknownHostException -> UNKNOWN_HOST_ERROR
                    is IOException -> NETWORK_ERROR
                    else -> UNKNOWN_ERROR
                }.run {
                    throwable.printStackTrace()
                    callback.invoke(NetworkResponse.NetworkError(this, throwable))
                }
            }
        })
    }

    override fun isExecuted() = delegate.isExecuted

    override fun clone() = NetworkResponseCall(delegate.clone(), errorConverter)

    override fun isCanceled() = delegate.isCanceled

    override fun cancel() = delegate.cancel()

    override fun execute(): Response<NetworkResponse<S, E>> {
        throw UnsupportedOperationException("NetworkResponseCall doesn't support execute")
    }

    override fun request(): Request = delegate.request()

    override fun timeout(): Timeout = delegate.timeout()

    private fun Callback<NetworkResponse<S, E>>.invoke(
        response: NetworkResponse<S, E>
    ) {
        onResponse(this@NetworkResponseCall, Response.success(response))
    }
}