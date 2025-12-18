package com.example.linktalk.data.repository

import com.example.linktalk.model.CreateAccount
import com.example.linktalk.model.Image
import com.example.linktalk.model.User
import kotlinx.coroutines.flow.Flow


interface AuthRepository {

    val currentUserFLow: Flow<User >

    suspend fun getAccessToken(): String?

    suspend fun clearAccessToken()

    suspend fun signUp(createAccount: CreateAccount) : Result<Unit>

    suspend fun signIn(username: String, password: String) : Result<Unit>

    suspend fun uploadProfilePicture(filePath: String) : Result<Image>

    suspend fun authenticate() : Result<Unit>
}