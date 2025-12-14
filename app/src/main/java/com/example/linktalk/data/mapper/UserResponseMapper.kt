package com.example.linktalk.data.mapper

import com.example.linktalk.data.network.model.UserResponse
import com.example.linktalk.model.User

fun UserResponse.asDomainModel() = User(
    id = this.id,
    self = false,
    firstName = this.firstName,
    lastName = this.lastName,
    profilePictureUrl = this.profilePictureUrl,
    username = this.username,
)