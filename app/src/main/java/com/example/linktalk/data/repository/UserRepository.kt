package com.example.linktalk.data.repository

import androidx.paging.PagingData
import com.example.linktalk.model.User
import kotlinx.coroutines.flow.Flow

interface UserRepository {
    fun getUsers(limit: Int = 10): Flow<PagingData<User>>
}