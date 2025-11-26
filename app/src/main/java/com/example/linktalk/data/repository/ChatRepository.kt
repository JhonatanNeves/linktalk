package com.example.linktalk.data.repository

import com.example.linktalk.model.Chat

interface ChatRepository {
    suspend fun getChats(offset: Int, limit: Int): Result<List<Chat>>
}