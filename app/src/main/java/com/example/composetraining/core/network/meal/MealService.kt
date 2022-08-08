package com.example.composetraining.core.network.meal

import com.example.composetraining.core.data.model.mealdb.response.CategoriesResponse
import com.example.composetraining.core.data.model.mealdb.response.MealDetailsResponse
import com.example.composetraining.core.data.model.mealdb.response.MealListResponse
import com.example.composetraining.core.data.model.mealdb.response.RandomMealResponse
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * https://www.themealdb.com/api.php
 */
interface MealService {

    @GET("random.php")
    suspend fun getRandomMeal(): RandomMealResponse

    @GET("categories.php")
    suspend fun getMealCategories(): CategoriesResponse

    /*www.themealdb.com/api/json/v1/1/filter.php?c=Seafood*/
    @GET("filter.php")
    suspend fun getMealsByCategory(
        @Query("c") name: String,
    ): MealListResponse

    /*www.themealdb.com/api/json/v1/1/lookup.php?i=52772*/
    @GET("lookup.php")
    suspend fun getMealDetails(
        @Query("i") idMeal: String,
    ): MealDetailsResponse
}