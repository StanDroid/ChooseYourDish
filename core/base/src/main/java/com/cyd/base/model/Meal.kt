package com.cyd.base.model

import com.cyd.base.utils.BASE_INGREDIENT_IMAGE_EXTENSION
import com.cyd.base.utils.BASE_INGREDIENT_IMAGE_URL

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
) {
    val imageUrl: String = BASE_INGREDIENT_IMAGE_URL + name + BASE_INGREDIENT_IMAGE_EXTENSION
}