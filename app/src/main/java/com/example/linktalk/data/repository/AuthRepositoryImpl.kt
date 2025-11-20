package com.example.linktalk.data.repository

import com.example.linktalk.data.di.IoDispatcher
import com.example.linktalk.data.manager.selfuser.SelfUserManager
import com.example.linktalk.data.manager.token.TokenManager
import com.example.linktalk.data.network.NetWorkDataSource
import com.example.linktalk.data.network.model.AuthRequest
import com.example.linktalk.data.network.model.CreatAccountRequest
import com.example.linktalk.model.CreateAccount
import com.example.linktalk.model.Image
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.withContext
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val networkDataSource: NetWorkDataSource,
    private val tokenManager: TokenManager,
    private val selfUserManager: SelfUserManager,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher,
) : AuthRepository {

    //CryptoManager foi criado apenas para validar a arquitetura e as funcionalidades básicas do Jetpack Security com EncryptedSharedPreferences

    override suspend fun getAccessToken(): String? {
        return tokenManager.accessToken.firstOrNull()
    }

    override suspend fun clearAccessToken() {
        withContext(ioDispatcher) {
            tokenManager.clearAccessToken()
        }
    }

    override suspend fun signUp(createAccount: CreateAccount): Result<Unit> {
        return withContext(ioDispatcher) {
            runCatching {
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
        }
    }

    override suspend fun signIn(username: String, password: String): Result<Unit> {
        return withContext(ioDispatcher) {
            runCatching {
                val tokenResponse = networkDataSource.signIn( // 1 armazena o token esperado
                    request = AuthRequest(
                        username = username,
                        password = password,
                    )
                )

                tokenManager.saveAccessToken(tokenResponse.token)
            }
        }
    }

    override suspend fun uploadProfilePicture(filePath: String): Result<Image> {
        return withContext(ioDispatcher) {
            runCatching {
                val imageResponse = networkDataSource.upLoadProfilePicture(filePath)
                Image(
                    id = imageResponse.id,
                    name = imageResponse.name,
                    type = imageResponse.type,
                    url = imageResponse.url,
                )
            }
        }
    }

    override suspend fun authenticate(token: String): Result<Unit> {
        return withContext(ioDispatcher) {
            runCatching {
                val userResponse = networkDataSource.authenticate(token)

                selfUserManager.saveSelfUserData(
                    firstName = userResponse.firstName,
                    lastName = userResponse.lastName,
                    profilePictureUrl = userResponse.profilePictureUrl ?: "",
                    userName = userResponse.username,
                )
            }
        }
    }

}