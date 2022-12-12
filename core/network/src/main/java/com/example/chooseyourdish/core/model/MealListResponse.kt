package com.example.chooseyourdish.core.model

data class MealListResponse(
    val meals: List<MealListItemDTO>?
)

data class MealListItemDTO(
    val idMeal: String?,
    val strMeal: String?,
    val strMealThumb: String?
)