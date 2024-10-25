package com.cyd.core.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.List
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Search
import androidx.compose.ui.graphics.vector.ImageVector
import kotlinx.serialization.Serializable


@Serializable
sealed class Screen(
    val route: String
) {

    fun withStringArgs(vararg args: String): String {
        return buildString {
            append(route)
            args.forEach {
                append("/$it")
            }
        }
    }
}

@Serializable
sealed class Graph(val route: String) {

    @Serializable
    data object SplashGraph : Graph("SplashRoute") {
        @Serializable
        data object SplashScreen : Screen("SplashScreen")

    }

    @Serializable
    data object MealDetailsScreen : Screen("MealDetails")

    @Serializable
    data object HomeGraph : Graph("HomeRoute") {
        @Serializable
        data object HomeScreen : Screen("BottomBarRootScreen%RandomMeal"), RootScreen
    }

    @Serializable
    data object CategoriesGraph : Graph("CategoriesRoute") {
        @Serializable
        data object CategoryListScreen : Screen("BottomBarRootScreen%Categories"), RootScreen

        @Serializable
        data object MealListScreen : Screen("CategoriesTabRoute%MealList")
    }

    @Serializable
    data object SearchGraph : Graph("SearchRoute") {
        @Serializable
        data object SearchScreen : Screen("BottomBarRootScreen%Search"), RootScreen
    }

    @Serializable
    data object FavoritesGraph : Graph("FavoritesRoute") {
        @Serializable
        data object FavoritesScreen : Screen("BottomBarRootScreen%Favorites"), RootScreen
    }
}

interface RootScreen

data class AppTab(
    val graph: String, val title: String, val icon: ImageVector
)

val mainTabs = listOf(
    AppTab(Graph.HomeGraph.route, "Home", Icons.Filled.Home),
    AppTab(Graph.CategoriesGraph.route, "Categories", Icons.AutoMirrored.Filled.List),
    AppTab(Graph.SearchGraph.route, "By Ingredient", Icons.Filled.Search),
    AppTab(Graph.FavoritesGraph.route, "Favorites", Icons.Filled.Favorite),
)