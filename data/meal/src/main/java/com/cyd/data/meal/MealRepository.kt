package com.cyd.data.meal

import com.cyd.base.model.Meal
import com.cyd.base.model.MealItem
import com.cyd.base.model.RandomMeal
import kotlinx.coroutines.flow.Flow

interface MealRepository {
    suspend fun getRandomMeal(): RandomMeal?

    suspend fun getMealsByCategory(name: String): List<MealItem>

    suspend fun getMealsByMainIngredient(name: String): List<MealItem>

    suspend fun getMealDetails(id: String): Meal?

    suspend fun getFavoritesMeals(): Flow<List<MealItem>>

    suspend fun insertFavoriteMeal(meal: MealItem)

    suspend fun removeFavoriteMeal(meal: MealItem)

    suspend fun getFavoritesMealIds(): List<String>
}