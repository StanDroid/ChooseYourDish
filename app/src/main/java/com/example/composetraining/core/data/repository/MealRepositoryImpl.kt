package com.example.composetraining.core.data.repository

import com.example.composetraining.core.data.model.mealdb.RandomMealResponse
import com.example.composetraining.core.network.meal.MealService
import io.reactivex.Single
import javax.inject.Inject

class MealRepositoryImpl @Inject constructor(
    private val mealService: MealService
) : MealRepository {

    override fun getRandomMeal(): Single<RandomMealResponse> {
        return mealService.getRandomMeal()
    }


}