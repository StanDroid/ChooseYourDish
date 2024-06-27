package com.cyd.base.model

data class Meal(
    val id: String? = null,
    val dateModified: String? = null,
    val area: String? = null,
    val category: String? = null,
    val creativeCommonsConfirmed: String? = null,
    val drinkAlternate: String? = null,
    val imageSource: String? = null,
    val ingredients: List<Ingredient> = emptyList(),
    val instructions: String? = null,
    val meal: String? = null,
    val mealThumb: String? = null,
    val source: String? = null,
    val tags: String? = null,
    val youtube: String? = null
)

data class Ingredient(
    val name: String,
    val measure: String?
)
