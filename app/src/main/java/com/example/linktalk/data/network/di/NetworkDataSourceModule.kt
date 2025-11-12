package com.example.linktalk.data.network.di

import com.example.linktalk.data.network.NetWorkDataSource
import com.example.linktalk.data.network.NetworkDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface NetworkDataSourceModule {
    @Binds
    @Singleton
    fun bindNetworkDataSource(networkDataSource: NetworkDataSourceImpl) : NetWorkDataSource
}