package com.example.chooseyourdish.core

import com.example.chooseyourdish.core.model.Category
import com.example.chooseyourdish.core.model.MealDetailsDTO
import com.example.chooseyourdish.core.model.MealListItemDTO
import com.example.chooseyourdish.core.model.RandomMealDTO

interface MealDataSource {

    suspend fun getRandomMeal(): RandomMealDTO?

    suspend fun getMealCategories(): List<Category>?

    suspend fun getMealsByCategory(name: String): List<MealListItemDTO>?

    suspend fun getMealDetails(idMeal: String): MealDetailsDTO?
}