package com.example.linktalk.data.repository

import com.example.linktalk.data.di.IoDispatcher
import com.example.linktalk.data.manager.selfuser.SelfUserManager
import com.example.linktalk.data.manager.token.TokenManager
import com.example.linktalk.data.mapper.asDomainModel
import com.example.linktalk.data.network.NetWorkDataSource
import com.example.linktalk.data.network.model.PaginationParams
import com.example.linktalk.model.Chat
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.withContext
import javax.inject.Inject

class ChatRepositoryImpl @Inject constructor(
    private val networkDataSource: NetWorkDataSource,
    private val tokenManager: TokenManager,
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
}