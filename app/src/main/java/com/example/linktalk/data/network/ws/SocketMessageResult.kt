package com.example.linktalk.data.network.ws

import com.example.linktalk.data.network.model.ActiveUserIdsResponse
import com.example.linktalk.data.network.model.MessageResponse

sealed interface SocketMessageResult {
    data object NotHandledYet : SocketMessageResult
    data class MessageReceived(val message: MessageResponse) : SocketMessageResult
    data class ActiveUsersChanged(val activeUserIdsResponse: ActiveUserIdsResponse) : SocketMessageResult
    data class ConnectionError(val error: Throwable) : SocketMessageResult
}