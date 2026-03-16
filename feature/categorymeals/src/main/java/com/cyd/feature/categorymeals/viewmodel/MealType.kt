package com.cyd.feature.categorymeals.viewmodel

sealed class MealType {
    data class Category(
        val name: String,
    ) : MealType()

    data class Ingredient(
        val name: String,
    ) : MealType()

    data object Favorites : MealType()
}
