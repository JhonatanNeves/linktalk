package com.example.linktalk.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.linktalk.data.database.dao.MessageDao
import com.example.linktalk.data.database.entity.MessageEntity


@Database(
    entities = [
        MessageEntity::class
    ],
    version = 1,
)
abstract class LinkTalkChatDatabase : RoomDatabase() {
    abstract fun messageDao(): MessageDao

}