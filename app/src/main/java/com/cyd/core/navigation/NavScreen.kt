package com.cyd.core.navigation

sealed class NavScreen(val route: String) {
    data object Splash : NavScreen("SplashScreen")
    data object RandomMeal : NavScreen("RandomMeal")
    data object CategoryList : NavScreen("CategoryList")
    data object MealList : NavScreen("MealList")
    data object MealDetails : NavScreen("MealDetails")
    data object Search : NavScreen("Search")

    fun withStringArgs(vararg args: String): String {
        return buildString {
            append(route)
            args.forEach {
                append("/$it")
            }
        }
    }
}