package com.example.linktalk.data.network.model

import kotlinx.serialization.Serializable

@Serializable
data class TokenResponse(
    val token: String,
)
