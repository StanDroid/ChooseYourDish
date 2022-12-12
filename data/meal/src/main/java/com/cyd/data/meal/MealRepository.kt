package com.cyd.data.meal

import com.cyd.core.network.model.MealDetailsDTO
import com.cyd.core.network.model.MealListItemDTO
import com.cyd.core.network.model.RandomMealDTO

interface MealRepository {
  suspend fun getRandomMeal(): RandomMealDTO?

  suspend fun getMealsByCategory(name: String): List<MealListItemDTO>?

  suspend fun getMealDetails(id: String): MealDetailsDTO?
}