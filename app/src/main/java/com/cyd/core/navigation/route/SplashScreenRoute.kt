package com.cyd.core.navigation.route

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import com.cyd.core.navigation.NavScreen
import com.cyd.core.navigation.SplashScreen

@Composable
fun SplashScreenRoute(
    navController: NavHostController
) {
    SplashScreen {
        navController.navigate(NavScreen.RandomMeal.route) {
            popUpTo(NavScreen.Splash.route) {
                inclusive = true
            }
        }
    }
}