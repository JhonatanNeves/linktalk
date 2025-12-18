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
import com.example.linktalk.data.manager.ChatNotificationManager
import com.example.linktalk.data.manager.selfuser.SelfUserManager
import com.example.linktalk.data.mapper.asDomainModel
import com.example.linktalk.data.network.NetworkDataSource
import com.example.linktalk.data.network.model.PaginationParams
import com.example.linktalk.data.network.ws.ChatWebSocketService
import com.example.linktalk.data.network.ws.SocketMessageResult
import com.example.linktalk.data.pagingsource.MessageRemoteMediator
import com.example.linktalk.data.util.safeCallResult
import com.example.linktalk.model.Chat
import com.example.linktalk.model.ChatMessage
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext
import javax.inject.Inject

class ChatRepositoryImpl @Inject constructor(
    private val networkDataSource: NetworkDataSource,
    private val databaseDataSource: DatabaseDataSource,
    private val database: LinkTalkChatDatabase,
    private val selfUserManager: SelfUserManager,
    private val chatNotificationManager: ChatNotificationManager,
    private val chatWebSocketService: ChatWebSocketService,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher,
) : ChatRepository {

    private val selfUser = runBlocking { selfUserManager.selfUserFlow.firstOrNull() }

    override val newMessageReceivedFlow: Flow<Unit>
        get() = chatNotificationManager.incomingMessageFlow.map { }

    override suspend fun getChats(offset: Int, limit: Int): Result<List<Chat>> {
        return withContext(ioDispatcher) {
            runCatching {
                val paginatedChatResponse = networkDataSource.getChats(
                    paginationParams = PaginationParams(
                        offset = offset.toString(),
                        limit = limit.toString()
                    )
                )

                paginatedChatResponse.asDomainModel(selfUser?.id)
            }
        }
    }

    @OptIn(ExperimentalPagingApi::class)
    override fun getPagedMessages(receiverId: Int): Flow<PagingData<ChatMessage>> {
        return Pager(
            config = PagingConfig(
                pageSize = 20,
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
                messageEntity.asDomainModel(selfUserId = selfUser?.id)
            }
        }.flowOn(ioDispatcher)
    }

    override suspend fun sendMessage(receiverId: Int, message: String): Result<Unit> {
        return safeCallResult(ioDispatcher) {
            chatWebSocketService.sendMessage(receiverId, message)
        }
    }

    override suspend fun connectWebsocket(): Result<Unit> {
        return safeCallResult(ioDispatcher) {
            chatWebSocketService.connect(selfUser?.id ?: 0)
        }
    }

    override fun observeSocketMessageResultFlow(): Flow<SocketMessageResult> {
        return chatWebSocketService.observerSocketMessageResultFlow()
            .onEach { socketMessageResult ->
                when (socketMessageResult) {
                    is SocketMessageResult.MessageReceived -> {
                        val messageResponse = socketMessageResult.message
                        val messageEntity = MessageEntity(
                            id = messageResponse.id,
                            isUnread = messageResponse.isUnread,
                            senderId = messageResponse.senderId,
                            receiverId = messageResponse.receiverId,
                            text = messageResponse.text,
                            timestamp = messageResponse.timestamp
                        )

                        databaseDataSource.insertMessages(listOf(messageEntity))
                    }
                    else -> {
                    }
                }
            }.flowOn(ioDispatcher)
    }

    override suspend fun disconnectWebsocket() {
        withContext(ioDispatcher) {
            chatWebSocketService.disconnect()
        }
    }
}