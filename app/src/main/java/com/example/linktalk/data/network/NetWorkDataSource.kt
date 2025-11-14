package com.example.linktalk.data.network

import com.example.linktalk.data.network.model.AuthRequest
import com.example.linktalk.data.network.model.CreatAccountRequest
import com.example.linktalk.data.network.model.ImageResponse
import com.example.linktalk.data.network.model.TokenResponse

interface NetWorkDataSource {
    suspend fun signUp(request: CreatAccountRequest)

    suspend fun signIn(request: AuthRequest): TokenResponse

    suspend fun upLoadProfilePicture(filePath: String): ImageResponse
}