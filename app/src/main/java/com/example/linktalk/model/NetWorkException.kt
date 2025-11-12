package com.example.linktalk.model

sealed class NetWorkException(message: String, cause: Throwable? = null) : Exception(message, cause){
    class ApiException(val responseMessage: String, val statusCode: Int) : NetWorkException(responseMessage)
    class UnknownNetworkException(cause: Throwable? = null) : NetWorkException("Unknown network exception", cause)
}