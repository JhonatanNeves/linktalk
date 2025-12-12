package com.example.linktalk.data.mapper

import com.example.linktalk.data.network.model.PaginatedChatResponse
import com.example.linktalk.model.Chat
import com.example.linktalk.model.User

fun PaginatedChatResponse.asDomainModel(selfUserId: Int?): List<Chat> =
    this.chats.map { chatResponse ->
        Chat(
            id = chatResponse.id,
            lastMessage = chatResponse.lastMessage,
            members = chatResponse.members.map { userResponse ->
                User(
                    id = userResponse.id,
                    self = userResponse.id == selfUserId,
                    firstName = userResponse.firstName,
                    lastName = userResponse.lastName,
                    profilePictureUrl = userResponse.profilePictureUrl,
                    username = userResponse.username,
                )
            },
            unreadCount = chatResponse.unreadCount,
            timestamp = chatResponse.updatedAt.toTimestamp()
        )
    }