package com.cyd.core.navigation

import androidx.activity.compose.BackHandler
import androidx.compose.runtime.Composable
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController


@Composable
fun CydBackHandler(navController: NavHostController) {
    BackHandler {
        navController.navigate(Graph.HomeGraph.route) {
            restoreState = true
            popUpTo(navController.graph.findStartDestination().id) {
                saveState = true
                inclusive = true
            }
        }
    }
}