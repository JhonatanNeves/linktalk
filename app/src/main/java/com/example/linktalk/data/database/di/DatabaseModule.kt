package com.example.linktalk.data.database.di

import android.content.Context
import androidx.room.Room
import com.example.linktalk.data.database.LinkTalkChatDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(
        @ApplicationContext context: Context
    ): LinkTalkChatDatabase = Room.databaseBuilder(
        context,
        LinkTalkChatDatabase::class.java,
        "linktalk_chat_database"
    ).build()
}