package com.example.linktalk.data.network.model

import kotlinx.serialization.Serializable

@Serializable
data class RegisterTokenRequest(
    val token: String
)
