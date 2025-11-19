package com.example.linktalk.data.manager.di

import com.example.linktalk.data.manager.SecureTokenManagerImpl
import com.example.linktalk.data.manager.TokenManager
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
}
