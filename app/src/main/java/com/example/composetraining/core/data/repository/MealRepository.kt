package com.example.composetraining.core.data.repository

import com.example.composetraining.core.data.model.mealdb.RandomMealResponse
import com.example.composetraining.core.data.model.mealdb.response.CategoriesResponse
import com.example.composetraining.core.data.model.mealdb.response.MealDetailsResponse
import com.example.composetraining.core.data.model.mealdb.response.MealListResponse
import io.reactivex.Single

interface MealRepository {
    fun getRandomMeal(): Single<RandomMealResponse>

    fun getMealCategories(): Single<CategoriesResponse>

    fun getMealsByCategory(name: String): Single<MealListResponse>

    fun getMealDetails(id: String): Single<MealDetailsResponse>
}