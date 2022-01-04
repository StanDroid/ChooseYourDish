package com.example.composetraining.core.data.repository

import com.example.composetraining.core.network.meal.MealService
import javax.inject.Inject

class MealRepositoryImpl @Inject constructor(
    private val mealService: MealService
) : MealRepository {

    override fun getRandomMeal() = mealService.getRandomMeal()

    override fun getMealCategories() = mealService.getMealCategories()
}