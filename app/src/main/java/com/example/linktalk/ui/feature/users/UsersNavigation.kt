package com.example.linktalk.ui.feature.users

import androidx.navigation.NavController
import androidx.navigation.NavOptions
import com.example.linktalk.navigation.Route

fun NavController.navigateToUsers(
    navOptions: NavOptions? = null
) {
    this.navigate(Route.UsersRoute, navOptions)
}