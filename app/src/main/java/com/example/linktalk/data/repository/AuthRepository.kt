package com.example.linktalk.data.repository

import com.example.linktalk.model.CreateAccount
import com.example.linktalk.model.Image


interface AuthRepository {
    suspend fun signUp(createAccount: CreateAccount) : Result<Unit>

    suspend fun signIn(username: String, password: String)

    suspend fun uploadProfilePicture(filePath: String) : Result<Image>
}