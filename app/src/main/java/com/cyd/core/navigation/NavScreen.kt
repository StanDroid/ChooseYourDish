package com.cyd.core.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.List
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Search
import androidx.compose.ui.graphics.vector.ImageVector


sealed class NavScreen(
    val route: String
) {
    data object Splash : NavScreen("SplashScreen")
    data object MealList : NavScreen("MealList")
    data object MealDetails : NavScreen("MealDetails")

    fun withStringArgs(vararg args: String): String {
        return buildString {
            append(route)
            args.forEach {
                append("/$it")
            }
        }
    }
}

sealed class BottomBarScreen(
    route: String,
    val title: String,
    val icon: ImageVector
) : NavScreen(route) {
    data object RandomMeal : BottomBarScreen("RandomMeal", "Home", Icons.Default.Home)
    data object CategoryList :
        BottomBarScreen("CategoryList", "Categories", Icons.AutoMirrored.Filled.List)

    data object Favorites : BottomBarScreen("Favorites", "Favorites", Icons.Default.Favorite)
    data object Search : BottomBarScreen("Search", "Search", Icons.Default.Search)
}