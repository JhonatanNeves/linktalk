package com.example.linktalk.data.manager.selfuser

import com.example.linktalk.SelfUser
import kotlinx.coroutines.flow.Flow

interface SelfUserManager {

    val selfUserFlow: Flow<SelfUser>

    suspend fun saveSelfUserData(
        id: Int,
        firstName: String,
        lastName: String,
        profilePictureUrl: String,
        userName: String,
    )

    suspend fun clearSelfUser()
}