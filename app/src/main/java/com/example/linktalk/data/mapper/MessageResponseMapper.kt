package com.example.linktalk.data.mapper

import com.example.linktalk.data.database.entity.MessageEntity
import com.example.linktalk.data.network.model.PaginatedMessageResponse

fun PaginatedMessageResponse.asEntityModel(): List<MessageEntity> = this.messages.map { messages ->
    MessageEntity(
        id = messages.id,
        isUnread = messages.isUnread,
        receiverId = messages.receiverId,
        senderId = messages.senderId,
        text = messages.text,
        timestamp = messages.timestamp,
    )
}