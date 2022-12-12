package com.example.chooseyourdish.core.data.repository

import com.example.chooseyourdish.core.network.meal.MealService
import javax.inject.Inject

class MealRepositoryImpl @Inject constructor(
    private val mealService: MealService
) : MealRepository {

    override suspend fun getRandomMeal() = mealService.getRandomMeal()

    override suspend fun getMealCategories() = mealService.getMealCategories()

    override suspend fun getMealsByCategory(name: String) = mealService.getMealsByCategory(name)

    override suspend fun getMealDetails(id: String) = mealService.getMealDetails(id)
}