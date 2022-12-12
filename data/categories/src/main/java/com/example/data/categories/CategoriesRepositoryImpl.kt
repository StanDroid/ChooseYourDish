package com.example.data.categories

import com.example.chooseyourdish.core.MealDataSource
import javax.inject.Inject

class CategoriesRepositoryImpl @Inject constructor(
    private val mealDataSource: MealDataSource
) : CategoriesRepository {

    override suspend fun getMealCategories() = mealDataSource.getMealCategories()
}