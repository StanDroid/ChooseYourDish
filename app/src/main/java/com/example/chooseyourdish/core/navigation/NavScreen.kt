package com.example.chooseyourdish.core.navigation

sealed class NavScreen(val route: String) {
    object RandomMeal : NavScreen("RandomMeal")
    object CategoryList : NavScreen("CategoryList")
    object MealList : NavScreen("MealList")
    object MealDetails : NavScreen("MealDetails")

    fun withStringArgs(vararg args: String): String {
        return buildString {
            append(route)
            args.forEach {
                append("/$it")
            }
        }
    }
}