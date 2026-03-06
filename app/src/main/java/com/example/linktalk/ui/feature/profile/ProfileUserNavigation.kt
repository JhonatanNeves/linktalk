package com.example.linktalk.ui.feature.profile

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.example.linktalk.navigation.Route

fun NavController.navigateToProfile(navOptions: NavOptions? = null
) {
    this.navigate(Route.ProfileRoute, navOptions)
}
