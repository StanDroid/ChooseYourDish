package com.example.data.meal

import com.example.chooseyourdish.core.model.MealDetailsDTO
import com.example.chooseyourdish.core.model.MealListItemDTO
import com.example.chooseyourdish.core.model.RandomMealDTO

interface MealRepository {
  suspend fun getRandomMeal(): RandomMealDTO?

  suspend fun getMealsByCategory(name: String): List<MealListItemDTO>?

  suspend fun getMealDetails(id: String): MealDetailsDTO?
}