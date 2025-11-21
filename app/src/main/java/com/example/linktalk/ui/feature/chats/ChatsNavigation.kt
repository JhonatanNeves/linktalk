package com.example.linktalk.ui.feature.chats

import androidx.navigation.NavController
import androidx.navigation.NavOptions
import com.example.linktalk.navigation.Route

fun NavController.navigateToChats(
    navOptions: NavOptions? = null
) {
    this.navigate(Route.ChatsRoute, navOptions)
}