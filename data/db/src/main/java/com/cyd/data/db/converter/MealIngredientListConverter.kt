package com.cyd.data.db.converter

import androidx.room.TypeConverter
import com.cyd.base.model.MealIngredient
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class MealIngredientListConverter {

    @TypeConverter
    fun fromMealIngredientList(mealIngredients: List<MealIngredient>): String {
        val gson = Gson()
        return gson.toJson(mealIngredients)
    }

    @TypeConverter
    fun toMealIngredientList(mealIngredientsString: String): List<MealIngredient> {
        val gson = Gson()
        val type = object : TypeToken<List<MealIngredient>>() {}.type
        return gson.fromJson(mealIngredientsString, type)
    }
}