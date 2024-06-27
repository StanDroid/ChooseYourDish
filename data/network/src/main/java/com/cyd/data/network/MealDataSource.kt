package com.cyd.data.network

import com.cyd.data.network.model.CategoryDTO
import com.cyd.data.network.model.MealDetailsDTO
import com.cyd.data.network.model.MealListItemDTO
import com.cyd.data.network.model.RandomMealDTO

interface MealDataSource {

    suspend fun getRandomMeal(): RandomMealDTO?

    suspend fun getMealCategories(): List<CategoryDTO>?

    suspend fun getMealsByCategory(name: String): List<MealListItemDTO>?

    suspend fun getMealDetails(idMeal: String): MealDetailsDTO?
}