package com.example.linktalk.model.fake

import com.example.linktalk.model.Chat
import com.example.linktalk.model.User

val chat1 = Chat(
    id = 1,
    lastMessage = "What's up!",
    members = listOf(
        user1,
        user2
    ),
    unreadCount = 0,
    timestamp = "12:25"
)

val chat2 = Chat(
    id = 2,
    lastMessage = "OMG!!!",
    members = listOf(
        user1,
        user3
    ),
    unreadCount = 4,
    timestamp = "15:20"
)

val chat3 = Chat(
    id = 3,
    lastMessage = "Let's do it!!!",
    members = listOf(
        user1,
        user4
    ),
    unreadCount = 2,
    timestamp = "00:00"
)
