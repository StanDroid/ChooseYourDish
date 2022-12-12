package com.example.chooseyourdish.core.data.repository

import com.example.chooseyourdish.core.data.model.mealdb.response.CategoriesResponse
import com.example.chooseyourdish.core.data.model.mealdb.response.MealDetailsResponse
import com.example.chooseyourdish.core.data.model.mealdb.response.MealListResponse
import com.example.chooseyourdish.core.data.model.mealdb.response.RandomMealResponse

interface MealRepository {
  suspend fun getRandomMeal(): RandomMealResponse

  suspend fun getMealCategories(): CategoriesResponse

  suspend fun getMealsByCategory(name: String): MealListResponse

  suspend fun getMealDetails(id: String): MealDetailsResponse
}