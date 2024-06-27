package com.cyd.feature.meal_details.usecase

import com.cyd.base.model.Meal
import com.cyd.base.usecase.UseCaseSuspend
import com.cyd.data.meal.MealRepository
import javax.inject.Inject

class GetMealDetailsUseCase @Inject constructor(
    private val repository: MealRepository
) : UseCaseSuspend<String, Meal> {

    override suspend fun execute(params: String): Meal {
        return repository.getMealDetails(params) ?: throw NoSuchElementException()
    }
}