package com.example.linktalk.data.pagingsource.di

import androidx.paging.PagingSource
import com.example.linktalk.data.network.NetWorkDataSource
import com.example.linktalk.data.pagingsource.UserPagingSource
import com.example.linktalk.model.User
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import jakarta.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object PagingSourceModule {

    @Provides
    @Singleton
    fun provideUserPagingSource(
        networkDataSource: NetWorkDataSource,
    ): PagingSource<Int, User> = UserPagingSource(networkDataSource)
}