package com.example.linktalk.navigation

import kotlinx.serialization.Serializable

sealed interface Route {
    @Serializable
    data object SplashRoute : Route

    @Serializable
    object SignInRoute

    @Serializable
    object SignUpRoute

    @Serializable
    object ChatsRoute

    @Serializable
    object UsersRoute

    @Serializable
    object ProfileRoute

    @Serializable
    data class ChatDetailRoute(val userId: Int)

}