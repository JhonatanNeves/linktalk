package com.example.linktalk.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.linktalk.data.database.dao.MessageDao
import com.example.linktalk.data.database.dao.MessageRemoteKeyDao
import com.example.linktalk.data.database.entity.MessageEntity
import com.example.linktalk.data.database.entity.MessageRemoteKeyEntity


@Database(
    entities = [
        MessageEntity::class,
        MessageRemoteKeyEntity::class
    ],
    version = 1,
)
abstract class LinkTalkChatDatabase : RoomDatabase() {
    abstract fun messageDao(): MessageDao

    abstract fun messageRemoteKeyDao(): MessageRemoteKeyDao

    abstract fun clearMessageRemoteKey(): MessageRemoteKeyDao



}