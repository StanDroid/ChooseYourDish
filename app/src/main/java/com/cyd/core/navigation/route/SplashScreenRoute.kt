package com.cyd.core.navigation.route

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import com.cyd.core.navigation.Graph
import com.cyd.core.navigation.SplashScreen

@Composable
fun SplashScreenRoute(
    navController: NavHostController
) {
    SplashScreen {
        navController.navigate(Graph.HomeGraph.route) {
            popUpTo(Graph.SplashGraph.SplashScreen.route) {
                inclusive = true
            }
        }
    }
}