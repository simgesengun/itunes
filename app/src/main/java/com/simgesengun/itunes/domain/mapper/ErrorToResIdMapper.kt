package com.simgesengun.itunes.domain.mapper

import com.simgesengun.itunes.R
import com.simgesengun.itunes.domain.mapper.base.Mapper
import com.simgesengun.itunes.network.model.NetworkErrorType
import com.simgesengun.itunes.network.model.NetworkResponse
import javax.inject.Inject

class ErrorToResIdMapper @Inject constructor(): Mapper<NetworkResponse<Any, Any>?, Int> {
    override fun map(from: NetworkResponse<Any, Any>?): Int {
        return when(from) {
            is NetworkResponse.ApiError -> R.string.technical_error
            is NetworkResponse.NetworkError -> {
                when(from.error) {
                    NetworkErrorType.NETWORK_ERROR,
                    NetworkErrorType.TIMEOUT_ERROR,
                    NetworkErrorType.UNKNOWN_HOST_ERROR -> R.string.network_error
                    NetworkErrorType.EMPTY_RESPONSE_ERROR,
                    NetworkErrorType.UNKNOWN_ERROR -> R.string.technical_error
                }
            }
            else -> R.string.error
        }
    }
}