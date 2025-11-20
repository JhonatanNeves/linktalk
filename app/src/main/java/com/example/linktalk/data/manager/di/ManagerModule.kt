package com.example.linktalk.data.manager.di

import com.example.linktalk.data.manager.selfuser.SelfUserManager
import com.example.linktalk.data.manager.selfuser.SelfUserManagerImpl
import com.example.linktalk.data.manager.token.SecureTokenManagerImpl
import com.example.linktalk.data.manager.token.TokenManager
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface TokenManagerModule {
    @Binds
    @Singleton
    fun bindTokenManager (tokenManager: SecureTokenManagerImpl): TokenManager

    @Binds
    @Singleton
    fun bindSelfUserManager (selfUserManager: SelfUserManagerImpl): SelfUserManager
}
