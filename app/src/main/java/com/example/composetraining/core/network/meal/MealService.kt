package com.example.composetraining.core.network.meal

import com.example.composetraining.core.data.model.mealdb.RandomMealResponse
import io.reactivex.Single
import retrofit2.http.GET

interface MealService {

    @GET("random.php")
    fun getRandomMeal(): Single<RandomMealResponse>
}