package com.example.linktalk.navigation

import android.annotation.SuppressLint
import androidx.activity.compose.LocalActivity
import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navOptions
import com.example.linktalk.navigation.extension.slideInTo
import com.example.linktalk.navigation.extension.slideOutTo
import com.example.linktalk.ui.feature.chatdetail.ChatDetailRoute
import com.example.linktalk.ui.feature.chats.ChatsRoute
import com.example.linktalk.ui.feature.chats.navigateToChats
import com.example.linktalk.ui.feature.signin.SingInRoute
import com.example.linktalk.ui.feature.signup.SignUpRoute
import com.example.linktalk.ui.feature.splash.SplashRoute
import com.example.linktalk.ui.feature.users.UsersRoute

@SuppressLint("ContextCastToActivity")
@Composable
fun ChatNavHost(
    navigationState: LinkTalkNavigationState
) {
    val navController = navigationState.navController
    val activity = LocalActivity.current

    NavHost(navController = navController, startDestination = Route.SplashRoute) {
        composable<Route.SplashRoute> {
            SplashRoute(
                onNavigateToSingIn = {
                    navController.navigate(
                        route = Route.SingInRoute,
                        navOptions = navOptions {
                            popUpTo(route = Route.SplashRoute) {
                                inclusive = true
                            }
                        }
                    )
                },
                onNavigateToMain = {
                    navController.navigateToChats(
                        navOptions = navOptions {
                            popUpTo(Route.SplashRoute) {
                                inclusive = true
                            }
                        }
                    )
                },
                onCloseApp = {
                    activity?.finish()
                },
            )
        }
        composable<Route.SingInRoute>(
            enterTransition = { this.slideInTo(AnimatedContentTransitionScope.SlideDirection.Right) },
            exitTransition = { this.slideOutTo(AnimatedContentTransitionScope.SlideDirection.Left) }
        ) {
            SingInRoute(
                navigateToSignUp = {
                    navController.navigate(Route.SingUpRoute)
                },
                navigateToMain = {
                    navController.navigateToChats(
                        navOptions = navOptions {
                            popUpTo(Route.SingInRoute) {
                                inclusive = true
                            }
                        }
                    )
                }
            )
        }
        composable<Route.SingUpRoute>(
            enterTransition = { this.slideInTo(AnimatedContentTransitionScope.SlideDirection.Left) },
            exitTransition = { this.slideOutTo(AnimatedContentTransitionScope.SlideDirection.Right) }
        ) {
            SignUpRoute(
                onSignUpSuccess = {
                    navController.popBackStack()
                }
            )
        }

        composable<Route.ChatsRoute> {
            ChatsRoute()
        }

        composable<Route.UsersRoute>{
            UsersRoute(
                navigateToChatDetail = { userId ->
                    navController.navigate(Route.ChatDetailRoute(userId))
                }
            )
        }

        composable<Route.ChatDetailRoute>{
            ChatDetailRoute(
                navigateBack = {
                    navController.popBackStack()
                }
            )
        }
    }
}