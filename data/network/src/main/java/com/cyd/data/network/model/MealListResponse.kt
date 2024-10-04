package com.cyd.data.network.model

import androidx.annotation.Keep

@Keep
data class MealListResponse(
    val meals: List<MealListItemDTO>?
)

@Keep
data class MealListItemDTO(
    val idMeal: String?,
    val strMeal: String?,
    val strMealThumb: String?
)