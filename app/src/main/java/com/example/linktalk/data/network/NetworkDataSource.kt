package com.example.linktalk.data.network

import com.example.linktalk.data.network.model.AuthRequest
import com.example.linktalk.data.network.model.CreatAccountRequest
import com.example.linktalk.data.network.model.ImageResponse
import com.example.linktalk.data.network.model.PaginatedChatResponse
import com.example.linktalk.data.network.model.PaginatedMessageResponse
import com.example.linktalk.data.network.model.PaginatedUserResponse
import com.example.linktalk.data.network.model.PaginationParams
import com.example.linktalk.data.network.model.TokenResponse
import com.example.linktalk.data.network.model.UserResponse

interface NetworkDataSource {
    suspend fun signUp(request: CreatAccountRequest)

    suspend fun signIn(request: AuthRequest): TokenResponse

    suspend fun upLoadProfilePicture(filePath: String): ImageResponse

    suspend fun authenticate(): UserResponse

    suspend fun getChats(paginationParams: PaginationParams): PaginatedChatResponse

    suspend fun getUser(userId: Int): UserResponse

    suspend fun getUsers(paginationParams: PaginationParams): PaginatedUserResponse

    suspend fun getMessages(receiverId: Int, paginationParams: PaginationParams): PaginatedMessageResponse

}
