package com.example.linktalk.data.network

import com.example.linktalk.model.NetWorkException
import io.ktor.client.call.body
import io.ktor.client.plugins.ClientRequestException
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.client.statement.bodyAsText

suspend fun <T> handleNetworkException(block: suspend () -> T ): T {
   return try {
        block()
    } catch (e: ClientRequestException){
        val errorMessage = e.response.bodyAsText()
        throw NetWorkException.ApiException(errorMessage, e.response.status.value)
    } catch (e: Exception) {
        throw NetWorkException.UnknownNetworkException(e)
    }
}