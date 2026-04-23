package com.cyd.feature.mealdetails.usecase

import com.cyd.base.model.Meal
import com.cyd.base.usecase.UseCase
import com.cyd.data.meal.MealRepository
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import javax.inject.Inject

class GetMealDetailsUseCase
@Inject
constructor(
    private val repository: MealRepository,
) : UseCase<String, Meal> {
    override suspend fun execute(params: String): Meal =
        coroutineScope {
            val isFavoriteTask = async { repository.getFavoritesMealIds().any { it == params } }
            val detailMealTask = async { repository.getMealDetails(params) }
            val mealDetails = detailMealTask.await() ?: throw NoSuchElementException()
            mealDetails.isFavorite = isFavoriteTask.await()
            mealDetails
        }
}
