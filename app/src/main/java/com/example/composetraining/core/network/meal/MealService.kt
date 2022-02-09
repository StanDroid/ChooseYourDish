package com.example.composetraining.core.network.meal

import com.example.composetraining.core.data.model.mealdb.RandomMealResponse
import com.example.composetraining.core.data.model.mealdb.response.CategoriesResponse
import com.example.composetraining.core.data.model.mealdb.response.MealDetailsResponse
import com.example.composetraining.core.data.model.mealdb.response.MealListResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * https://www.themealdb.com/api.php
 */
interface MealService {

    @GET("random.php")
    fun getRandomMeal(): Single<RandomMealResponse>

    @GET("categories.php")
    fun getMealCategories(): Single<CategoriesResponse>

    /*www.themealdb.com/api/json/v1/1/filter.php?c=Seafood*/
    @GET("filter.php")
    fun getMealsByCategory(
        @Query("c") name: String,
    ): Single<MealListResponse>

    /*www.themealdb.com/api/json/v1/1/lookup.php?i=52772*/
    @GET("lookup.php")
    fun getMealDetails(
        @Query("i") idMeal: String,
    ): Single<MealDetailsResponse>
}