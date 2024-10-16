package com.cyd.core.navigation.route

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import com.cyd.core.navigation.BottomBarScreen
import com.cyd.core.navigation.Screen
import com.cyd.core.navigation.SplashScreen

@Composable
fun SplashScreenRoute(
    navController: NavHostController
) {
    SplashScreen {
        navController.navigate(BottomBarScreen.RandomMeal.route) {
            popUpTo(Screen.Splash.route) {
                inclusive = true
            }
        }
    }
}