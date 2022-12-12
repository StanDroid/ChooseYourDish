package com.cyd.core.network

import com.cyd.core.network.model.Category
import com.cyd.core.network.model.MealDetailsDTO
import com.cyd.core.network.model.MealListItemDTO
import com.cyd.core.network.model.RandomMealDTO

interface MealDataSource {

    suspend fun getRandomMeal(): RandomMealDTO?

    suspend fun getMealCategories(): List<Category>?

    suspend fun getMealsByCategory(name: String): List<MealListItemDTO>?

    suspend fun getMealDetails(idMeal: String): MealDetailsDTO?
}