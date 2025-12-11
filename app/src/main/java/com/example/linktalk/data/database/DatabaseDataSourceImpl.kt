package com.example.linktalk.data.database

import androidx.paging.PagingSource
import com.example.linktalk.data.database.entity.MessageEntity
import javax.inject.Inject

class DatabaseDataSourceImpl @Inject constructor(
    database: LinkTalkChatDatabase
) : DatabaseDataSource  {

    private val messageDao = database.messageDao()


    override fun getPagedMessages(receiverId: Int): PagingSource<Int, MessageEntity> {
        return messageDao.getPagedMessages(receiverId)
    }

    override suspend fun insertMessages(messages: List<MessageEntity>) {
        return messageDao.insertMessages(messages)
    }

    override suspend fun clearMessages(receiverId: Int) {
        return messageDao.clearMessages(receiverId)
    }
}