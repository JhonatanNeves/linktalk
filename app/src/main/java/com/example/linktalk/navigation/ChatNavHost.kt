package com.example.linktalk.navigation

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navOptions
import com.example.linktalk.navigation.extension.slideInTo
import com.example.linktalk.navigation.extension.slideOutTo
import com.example.linktalk.ui.feature.singin.SingInRoute
import com.example.linktalk.ui.feature.splash.SplashRoute
import kotlinx.serialization.Serializable

@Serializable
object SplashRoute

@Serializable
object SingInRoute

@Serializable
object SingUpRoute

@Composable
fun ChatNavHost() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = SplashRoute){
        composable<SplashRoute> {
            SplashRoute(
                onNavigateToSingIn = {
                    navController.navigate(
                        route = SingInRoute,
                        navOptions = navOptions {
                            popUpTo(route = SplashRoute) {
                                inclusive = true
                            }
                        }
                    )
                }
            )
        }
        composable<SingInRoute> (
            enterTransition = {this.slideInTo(AnimatedContentTransitionScope.SlideDirection.Right)},
            exitTransition = {this.slideOutTo(AnimatedContentTransitionScope.SlideDirection.Left)}
        ){
            SingInRoute(
                navigateToSignUp = {
                    navController.navigate(SingUpRoute)
                }
            )
        }
        composable<SingUpRoute> (
            enterTransition = {this.slideInTo(AnimatedContentTransitionScope.SlideDirection.Right)},
            exitTransition = {this.slideOutTo(AnimatedContentTransitionScope.SlideDirection.Left)}
        ){
            // todo
        }
    }
}