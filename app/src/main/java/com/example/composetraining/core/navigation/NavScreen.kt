package com.example.composetraining.core.navigation

sealed class NavScreen(val route: String) {
    object RandomMeal : NavScreen("RandomMeal")
    object CategoryList : NavScreen("CategoryList")
    object CategoryDetails : NavScreen("CategoryDetails")

    fun withStringArgs(vararg args: String): String {
        return buildString {
            append(route)
            args.forEach {
                append("/$it")
            }
        }
    }
}