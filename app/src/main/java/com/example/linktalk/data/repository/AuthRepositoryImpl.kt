package com.example.linktalk.data.repository

import com.example.linktalk.data.network.NetWorkDataSource
import com.example.linktalk.data.network.model.AuthRequest
import com.example.linktalk.data.network.model.CreatAccountRequest
import com.example.linktalk.model.CreateAccount
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val networkDataSource: NetWorkDataSource,
): AuthRepository {
    override suspend fun signUp(createAccount: CreateAccount) {
        networkDataSource.signUp(
            request = CreatAccountRequest(
                username = createAccount.username,
                password = createAccount.password,
                firstName = createAccount.firstName,
                lastName = createAccount.lastName,
                profilePictureId = createAccount.profilePictureId,
            )
        )
    }

    override suspend fun signIn(username: String, password: String) {
        networkDataSource.signIn(
            request = AuthRequest(
                username = username,
                password = password,
            )
        )
    }
}