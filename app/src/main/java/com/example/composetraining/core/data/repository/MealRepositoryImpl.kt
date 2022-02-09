package com.example.composetraining.core.data.repository

import com.example.composetraining.core.network.meal.MealService
import javax.inject.Inject

class MealRepositoryImpl @Inject constructor(
    private val mealService: MealService
) : MealRepository {

    override fun getRandomMeal() = mealService.getRandomMeal()

    override fun getMealCategories() = mealService.getMealCategories()

    override fun getMealsByCategory(name: String) = mealService.getMealsByCategory(name)

    override fun getMealDetails(id: String) = mealService.getMealDetails(id)
}