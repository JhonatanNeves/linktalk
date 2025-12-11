package com.example.linktalk.data.pagingsource

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import com.example.linktalk.data.database.DatabaseDataSource
import com.example.linktalk.data.database.LinkTalkChatDatabase
import com.example.linktalk.data.database.entity.MessageEntity
import com.example.linktalk.data.database.entity.MessageRemoteKeyEntity
import com.example.linktalk.data.mapper.asEntityModel
import com.example.linktalk.data.network.NetWorkDataSource
import com.example.linktalk.data.network.model.PaginationParams

@OptIn(ExperimentalPagingApi::class)
class MessageRemoteMediator(
    private val networkDataSource: NetWorkDataSource,
    private val databaseDataSource: DatabaseDataSource,
    private val database: LinkTalkChatDatabase,
    private val receiverId: Int,
) : RemoteMediator<Int, MessageEntity>() {
    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, MessageEntity>
    ): MediatorResult {
        return try {
            val offset = when (loadType) {
                LoadType.REFRESH -> 0
                LoadType.PREPEND -> return MediatorResult.Success(endOfPaginationReached = true)
                LoadType.APPEND -> {
                    val remoteKey = databaseDataSource.getMessageRemoteKey(receiverId)
                    remoteKey?.nextOffset
                        ?: return MediatorResult.Success(endOfPaginationReached = true)
                }
            }

            val limit = state.config.pageSize
            val paginationParams = PaginationParams(
                offset = offset.toString(),
                limit = limit.toString(),
            )

            val response = networkDataSource.getMessages(
                receiverId = receiverId,
                paginationParams = paginationParams,
            )

            val entities = response.asEntityModel()

            database.withTransaction {
                if (loadType == LoadType.REFRESH) {
                    databaseDataSource.clearMessageRemoteKey(receiverId)
                    databaseDataSource.clearMessages(receiverId)
                }

                databaseDataSource.insertMessageRemoteKey(
                    MessageRemoteKeyEntity(
                        receiverId = receiverId,
                        nextOffset = if(response.hasMore) {
                            offset + limit
                        } else null,
                    )
                )

                databaseDataSource.insertMessages(
                    messages = entities
                )
            }

                    MediatorResult.Success(endOfPaginationReached = !response.hasMore)
        } catch (e: Exception) {
            MediatorResult.Error(e)
        }
    }
}