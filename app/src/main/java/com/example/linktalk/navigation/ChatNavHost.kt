package com.example.linktalk.navigation

import androidx.activity.compose.LocalActivity
import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navOptions
import com.example.linktalk.navigation.extension.slidOutTo
import com.example.linktalk.navigation.extension.slideInTo
import com.example.linktalk.ui.feature.chatdetail.ChatDetailRoute
import com.example.linktalk.ui.feature.chats.ChatsRoute
import com.example.linktalk.ui.feature.chats.navigateToChats
import com.example.linktalk.ui.feature.signin.SignInRoute
import com.example.linktalk.ui.feature.signup.SignUpRoute
import com.example.linktalk.ui.feature.splash.SplashRoute
import com.example.linktalk.ui.feature.users.UsersRoute

@Composable
fun ChatNavHost(
    navigationState: LinkTalkNavigationState
) {
    val navController = navigationState.navController
    val activity = LocalActivity.current

    NavHost(navController = navController, startDestination = navigationState.startDestination) {
        composable<Route.SplashRoute> {
            SplashRoute(
                onNavigateToSignIn = {
                    navController.navigate(
                        route = Route.SignInRoute,
                        navOptions = navOptions {
                            popUpTo(Route.SplashRoute) {
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
                }
            )
        }
        composable<Route.SignInRoute>(
            enterTransition = {
                this.slideInTo(AnimatedContentTransitionScope.SlideDirection.Right)
            },
            exitTransition = {
                this.slidOutTo(AnimatedContentTransitionScope.SlideDirection.Left)
            }
        ) {
            SignInRoute(
                navigateToSignUp = {
                    navController.navigate(Route.SignUpRoute)
                },
                navigateToMain = {
                    navController.navigateToChats(
                        navOptions = navOptions {
                            popUpTo(Route.SignInRoute) {
                                inclusive = true
                            }
                        }
                    )
                }
            )
        }
        composable<Route.SignUpRoute>(
            enterTransition = {
                this.slideInTo(AnimatedContentTransitionScope.SlideDirection.Left)
            },
            exitTransition = {
                this.slidOutTo(AnimatedContentTransitionScope.SlideDirection.Right)
            }
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