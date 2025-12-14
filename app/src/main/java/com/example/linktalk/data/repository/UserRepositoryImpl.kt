package com.example.linktalk.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.linktalk.data.di.IoDispatcher
import com.example.linktalk.data.mapper.asDomainModel
import com.example.linktalk.data.network.NetWorkDataSource
import com.example.linktalk.data.pagingsource.UserPagingSource
import com.example.linktalk.data.util.safeCallResult
import com.example.linktalk.model.User
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val netWorkDataSource: NetWorkDataSource,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher,
) : UserRepository {

    override suspend fun getUser(userId: Int): Result<User> {
        return safeCallResult(ioDispatcher) {
            val userResponse = netWorkDataSource.getUser(userId)
            userResponse.asDomainModel()
        }
    }

    override fun getUsers(limit: Int): Flow<PagingData<User>> {
        return Pager(
            config = PagingConfig(
                prefetchDistance = 3,
                pageSize = 1,
                enablePlaceholders = false,
            ),
            pagingSourceFactory = { UserPagingSource(netWorkDataSource)}
        ).flow
    }
}