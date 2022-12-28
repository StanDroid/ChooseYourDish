package com.cyd.data.meal

import com.cyd.base.mealdb.Meal
import com.cyd.base.mealdb.MealItem
import com.cyd.base.mealdb.RandomMeal

interface MealRepository {
  suspend fun getRandomMeal(): RandomMeal?

  suspend fun getMealsByCategory(name: String): List<MealItem>

  suspend fun getMealDetails(id: String): Meal?
}