package com.example.linktalk.data.repository

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.map
import com.example.linktalk.data.database.DatabaseDataSource
import com.example.linktalk.data.database.LinkTalkChatDatabase
import com.example.linktalk.data.database.entity.MessageEntity
import com.example.linktalk.data.di.IoDispatcher
import com.example.linktalk.data.manager.selfuser.SelfUserManager
import com.example.linktalk.data.mapper.asDomainModel
import com.example.linktalk.data.network.NetWorkDataSource
import com.example.linktalk.data.network.model.PaginationParams
import com.example.linktalk.data.pagingsource.MessageRemoteMediator
import com.example.linktalk.model.Chat
import com.example.linktalk.model.ChatMessage
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext
import java.time.Instant
import javax.inject.Inject

class ChatRepositoryImpl @Inject constructor(
    private val networkDataSource: NetWorkDataSource,
    private val databaseDataSource: DatabaseDataSource,
    private val database: LinkTalkChatDatabase,
    private val selfUserManager: SelfUserManager,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher,
) : ChatRepository {
    override suspend fun getChats(offset: Int, limit: Int): Result<List<Chat>> {
        return withContext(ioDispatcher) {
            runCatching {
                val paginatedChatResponse = networkDataSource.getChats(
                    paginationParams = PaginationParams(
                        offset = offset.toString(),
                        limit = limit.toString()
                    )
                )
                val selfUser = selfUserManager.selfUserFlow.firstOrNull()
                paginatedChatResponse.asDomainModel(selfUser?.id)
            }
        }
    }

    @OptIn(ExperimentalPagingApi::class)
    override fun getPagedMessages(receiverId: Int): Flow<PagingData<ChatMessage>> {
        val selfUserId = runBlocking { selfUserManager.selfUserFlow.firstOrNull() }
        return Pager(
            config = PagingConfig(
                pageSize = 10,
                enablePlaceholders = false,
            ),
            remoteMediator = MessageRemoteMediator(
                networkDataSource = networkDataSource,
                databaseDataSource = databaseDataSource,
                database = database,
                receiverId = receiverId,
            ),
            pagingSourceFactory = {
                databaseDataSource.getPagedMessages(receiverId)
            }
        ).flow.map {
            it.map { messageEntity ->
                messageEntity.asDomainModel(selfUserId = selfUserId?.id)
            }
        }
    }

    override suspend fun sendMessage(receiverId: Int, message: String) {
        val selfUserId = selfUserManager.selfUserFlow.firstOrNull()
        val messageEntity = MessageEntity(
            id = null,
            isUnread = false,
            senderId = selfUserId?.id ?: return,
            receiverId = receiverId,
            text = message,
            timestamp = Instant.now().toEpochMilli()
        )

        databaseDataSource.insertMessages(listOf(messageEntity))

    }
}