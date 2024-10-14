package com.cyd.data.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.cyd.base.model.MealItem

@Entity(tableName = "favorite_meals")
data class FavoriteMealEntity(
    @PrimaryKey val id: Int,
    val mealItem: MealItem
)