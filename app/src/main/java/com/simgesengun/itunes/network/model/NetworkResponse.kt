package com.simgesengun.itunes.network.model

sealed class NetworkResponse<out S : Any, out E: Any> {

    data class Success<S : Any>(
        val body: S
    ) : NetworkResponse<S, Nothing>()

    data class ApiError<E: Any>(
        val errorBody: E,
        val code: Int
    ) : NetworkResponse<Nothing, E>()

    data class NetworkError(
        val error: NetworkErrorType,
        val exception: Throwable? = null
    ) : NetworkResponse<Nothing, Nothing>()
}