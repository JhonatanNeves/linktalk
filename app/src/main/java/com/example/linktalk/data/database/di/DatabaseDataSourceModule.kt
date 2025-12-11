package com.example.linktalk.data.database.di

import com.example.linktalk.data.database.DatabaseDataSource
import com.example.linktalk.data.database.DatabaseDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface DatabaseDataSourceModule {
    @Binds
    fun bindDatabaseDataSource(
        databaseDataSource: DatabaseDataSourceImpl
    ): DatabaseDataSource
}

