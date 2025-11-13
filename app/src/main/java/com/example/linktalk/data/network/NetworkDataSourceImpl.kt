package com.example.linktalk.data.network

import com.example.linktalk.data.network.model.AuthRequest
import com.example.linktalk.data.network.model.CreatAccountRequest
import com.example.linktalk.data.network.model.TokenResponse
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import javax.inject.Inject

class NetworkDataSourceImpl @Inject constructor(
    private val httpClient: HttpClient
) : NetWorkDataSource {
    override suspend fun signUp(request: CreatAccountRequest) {
        httpClient.post("signup") {
            setBody(request)
        }.body<Unit>()
    }

    override suspend fun signIn(request: AuthRequest): TokenResponse {
        return httpClient.post("signin") {
            setBody(request)
        }.body()
    }
}
