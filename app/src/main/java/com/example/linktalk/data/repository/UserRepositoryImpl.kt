package com.example.linktalk.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.linktalk.data.network.NetWorkDataSource
import com.example.linktalk.data.pagingsource.UserPagingSource
import com.example.linktalk.model.User
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val netWorkDataSource: NetWorkDataSource
) : UserRepository {

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