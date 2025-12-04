package com.example.linktalk.data.mapper

import com.example.linktalk.data.network.model.PaginatedUserResponse
import com.example.linktalk.model.User

fun PaginatedUserResponse.asDomainModel(): List<User> = this.users.map { userResponse ->
    User(
        id = userResponse.id,
        self = false,
        firstName = userResponse.firstName,
        lastName = userResponse.lastName,
        profilePictureUrl = userResponse.profilePictureUrl,
        username = userResponse.username,

    )
}