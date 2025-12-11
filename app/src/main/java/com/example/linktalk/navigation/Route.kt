package com.example.linktalk.navigation

import kotlinx.serialization.Serializable

sealed interface Route {
    @Serializable
    object SplashRoute

    @Serializable
    object SingInRoute

    @Serializable
    object SingUpRoute

    @Serializable
    object ChatsRoute

    @Serializable
    object UsersRoute

    @Serializable
    object ProfileRoute

    @Serializable
    data class ChatDetailRoute(val userId: Int)

}