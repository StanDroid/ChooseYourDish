package com.cyd.core.navigation

import androidx.annotation.DrawableRes
import com.cyd.ui.R
import kotlinx.serialization.Serializable

@Serializable
sealed class Screen(
    val route: String,
) {
    fun withStringArgs(vararg args: String): String =
        buildString {
            append(route)
            args.forEach {
                append("/$it")
            }
        }
}

@Serializable
sealed class Graph(
    val route: String,
) {
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
    val graph: String,
    val title: String,
    @param:DrawableRes val icon: Int,
)

val mainTabs =
    listOf(
        AppTab(Graph.HomeGraph.route, "Home", R.drawable.fork_spoon),
        AppTab(Graph.CategoriesGraph.route, "Categories", R.drawable.list),
        AppTab(Graph.SearchGraph.route, "Search", R.drawable.search),
        AppTab(Graph.FavoritesGraph.route, "Favorites", R.drawable.favorite),
    )
