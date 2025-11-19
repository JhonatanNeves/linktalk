package com.example.linktalk.data.manager.di

import com.example.linktalk.data.manager.TokenManager
import com.example.linktalk.data.manager.TokenManagerImpl
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
    fun bindTokenManager (tokenManager: TokenManagerImpl): TokenManager
}
