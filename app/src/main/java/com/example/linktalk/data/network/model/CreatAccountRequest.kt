package com.example.linktalk.data.network.model

import kotlinx.serialization.Serializable

@Serializable
data class CreatAccountRequest (
    val username: String,
    val password: String,
    val firstName: String,
    val lastName: String,
    val profilePictureId: String?,
)