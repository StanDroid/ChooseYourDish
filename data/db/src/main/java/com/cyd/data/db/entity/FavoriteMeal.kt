package com.cyd.data.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.cyd.base.model.MealIngredient

@Entity(tableName = "favorite_meals")
data class FavoriteMealEntity(
    @PrimaryKey val id: String,
    val dateModified: String? = null,
    val area: String? = null,
    val category: String? = null,
    val creativeCommonsConfirmed: String? = null,
    val drinkAlternate: String? = null,
    val imageSource: String? = null,
    val mealIngredients: List<MealIngredient> = emptyList(),
    val instructions: String? = null,
    val meal: String? = null,
    val mealThumb: String? = null,
    val source: String? = null,
    val tags: String? = null,
    val youtube: String? = null
)