package com.example.linktalk.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.remember
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hasRoute
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navOptions
import com.example.linktalk.ui.feature.chats.navigateToChats

@Composable
fun rememberLinkTalkNavigationState(
    navController: NavHostController = rememberNavController()
): LinkTalkNavigationState {
    return remember(navController) {
        LinkTalkNavigationState(navController)
    }
}

@Stable
class LinkTalkNavigationState(
    val navController: NavHostController,
) {
    val currentDestination: NavDestination?
        @Composable
        get() = navController
            .currentBackStackEntryAsState().value?.destination

    val currentTopLevelDestination: TopLevelDestination?
        @Composable
        get() = TopLevelDestination.entries.firstOrNull { topLevelDestination ->
            currentDestination?.hasRoute(topLevelDestination.route) == true
        }

    val topLevelDestination = TopLevelDestination.entries

    fun navigationToTopLevelDestination(topLevelDestination: TopLevelDestination) {
        val topLevelNavOptions = navOptions {
            popUpTo(navController.graph.startDestinationId) {
                saveState = true
            }
            launchSingleTop = true
            restoreState = true
        }

        when (topLevelDestination) {
            TopLevelDestination.CHATS -> navController.navigateToChats(topLevelNavOptions)
            TopLevelDestination.PLUS_BUTTON -> {}
            TopLevelDestination.PROFILE -> {}
        }
    }
}