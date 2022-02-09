package com.example.composetraining.core.data.model.mealdb.response

data class MealListResponse(
    val meals: List<MealListItemDTO>?
)

data class MealListItemDTO(
    val idMeal: String?,
    val strMeal: String?,
    val strMealThumb: String?
)