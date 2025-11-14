package com.example.linktalk.navigation

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navOptions
import com.example.linktalk.navigation.extension.slideInTo
import com.example.linktalk.navigation.extension.slideOutTo
import com.example.linktalk.ui.feature.signin.SingInRoute
import com.example.linktalk.ui.feature.signup.SignUpRoute
import com.example.linktalk.ui.feature.splash.SplashRoute
import kotlinx.serialization.Serializable

sealed interface Route {
    @Serializable
    object SplashRoute

    @Serializable
    object SingInRoute

    @Serializable
    object SingUpRoute
}

@Composable
fun ChatNavHost() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = Route.SplashRoute){
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
                }
            )
        }
        composable<Route.SingInRoute> (
            enterTransition = {this.slideInTo(AnimatedContentTransitionScope.SlideDirection.Right)},
            exitTransition = {this.slideOutTo(AnimatedContentTransitionScope.SlideDirection.Left)}
        ){
            SingInRoute(
                navigateToSignUp = {
                    navController.navigate(Route.SingUpRoute)
                }
            )
        }
        composable<Route.SingUpRoute> (
            enterTransition = {this.slideInTo(AnimatedContentTransitionScope.SlideDirection.Left)},
            exitTransition = {this.slideOutTo(AnimatedContentTransitionScope.SlideDirection.Right)}
        ){
            SignUpRoute(
                onSignUpSuccess = {
                    navController.popBackStack()
                }
            )
        }
    }
}