package com.example.data.meal

import com.example.chooseyourdish.core.MealDataSource
import javax.inject.Inject

class MealRepositoryImpl @Inject constructor(
    private val mealDataSource: MealDataSource
) : MealRepository {

    override suspend fun getRandomMeal() = mealDataSource.getRandomMeal()

    override suspend fun getMealsByCategory(name: String) = mealDataSource.getMealsByCategory(name)

    override suspend fun getMealDetails(id: String) = mealDataSource.getMealDetails(id)
}