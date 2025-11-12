package com.example.linktalk.data.repository.di

import com.example.linktalk.data.repository.AuthRepository
import com.example.linktalk.data.repository.AuthRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
interface RepositoryModule {
    @Binds
    fun bindAuthRepository(repository: AuthRepositoryImpl): AuthRepository
}