package com.example.linktalk.data.database

import androidx.paging.PagingSource
import com.example.linktalk.data.database.entity.MessageEntity
import com.example.linktalk.data.database.entity.MessageRemoteKeyEntity

interface DatabaseDataSource {
    fun getPagedMessages(receiverId: Int): PagingSource<Int, MessageEntity>

    suspend fun insertMessages(messages: List<MessageEntity>)

    suspend fun clearMessages(receiverId: Int)

    suspend fun getMessageRemoteKey(receiverId: Int): MessageRemoteKeyEntity?

    suspend fun insertMessageRemoteKey(remoteKey: MessageRemoteKeyEntity)

    suspend fun clearMessageRemoteKey(receiverId: Int)

}