package com.example.linktalk.data.mapper

import com.example.linktalk.data.database.entity.MessageEntity
import com.example.linktalk.model.ChatMessage

fun MessageEntity.asDomainModel(selfUserId: Int?): ChatMessage {
    return ChatMessage(
        autoId = this.autoId,
        id = this.id,
        senderId = this.senderId,
        receiverId = this.receiverId,
        text = this.text,
        formattedDateTime = this.timestamp.toTimestamp(),
        isUnread = this.isUnread,
        isSelf = this.senderId == selfUserId
    )
}