package com.cyd.core.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.List
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Search
import androidx.compose.ui.graphics.vector.ImageVector


sealed class Screen(
    val route: String
) {
    data object Splash : Screen("SplashScreen")
    data object MealList : Screen("MealList")
    data object MealDetails : Screen("MealDetails")

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
) : Screen(route) {
    data object RandomMeal : BottomBarScreen("RandomMeal", "Home", Icons.Default.Home)
    data object CategoryList :
        BottomBarScreen("CategoryList", "Categories", Icons.AutoMirrored.Filled.List)

    data object Favorites :
        BottomBarScreen("Favorites", "Favorites", Icons.Default.Favorite)

    data object Search : BottomBarScreen("Search", "By Ingredient", Icons.Default.Search)
}

sealed class GraphRoute(val route: String) {
    data object Splash : GraphRoute("SplashRoute")
    data object Home : GraphRoute("HomeRoute")
    data object Categories : GraphRoute("CategoriesRoute")
    data object Search : GraphRoute("SearchRoute")
    data object Favorites : GraphRoute("FavoritesRoute")
}