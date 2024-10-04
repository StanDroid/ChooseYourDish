package com.cyd.base.model

import androidx.annotation.Keep

@Keep
data class RandomMeal(
    val idMeal: String,
    val strMeal: String,
    val strArea: String,
    val strCategory: String,
    val strInstructions: String,
    val strMealThumb: String,
    val strYoutube: String,
    val strSource: String
)
