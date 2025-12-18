package com.example.linktalk.data.mapper

import com.example.linktalk.SelfUser
import com.example.linktalk.model.User

fun SelfUser.asDomainModel() = User (
    id = this.id,
    self = true,
    firstName = this.firstName,
    lastName = this.lastName,
    profilePictureUrl = this.profilePictureUrl,
    username = this.username,
)