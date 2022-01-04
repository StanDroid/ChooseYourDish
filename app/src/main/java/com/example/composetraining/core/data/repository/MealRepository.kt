package com.example.composetraining.core.data.repository

import com.example.composetraining.core.data.model.mealdb.RandomMealResponse
import com.example.composetraining.core.data.model.mealdb.response.CategoriesResponse
import io.reactivex.Single

interface MealRepository {
    fun getRandomMeal(): Single<RandomMealResponse>

    fun getMealCategories(): Single<CategoriesResponse>
}