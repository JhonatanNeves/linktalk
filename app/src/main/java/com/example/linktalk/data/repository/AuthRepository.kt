package com.example.linktalk.data.repository

import com.example.linktalk.model.CreateAccount


interface AuthRepository {
    suspend fun signUp(createAccount: CreateAccount)

    suspend fun signIn(username: String, password: String)
}