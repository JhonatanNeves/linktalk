package com.example.linktalk.data.network.model

import com.example.linktalk.data.network.serializer.WebSocketDataSerializer
import kotlinx.serialization.Serializable

@Serializable(with = WebSocketDataSerializer::class)
data class WebSocketData(
    val type: String,
    val data: Any
)
