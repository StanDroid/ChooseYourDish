package com.example.composetraining.core.navigation

sealed class NavScreen(val route: String) {
    object RandomMeal : NavScreen("RandomMeal")
    object MealList : NavScreen("MealList")
}