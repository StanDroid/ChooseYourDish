package com.cyd.feature.random_meal.usecase

import com.cyd.base.mealdb.RandomMeal
import com.cyd.base.usecase.UseCaseSuspend
import com.cyd.data.meal.MealRepository
import javax.inject.Inject

class GetRandomMealUseCase @Inject constructor(
    private val repository: MealRepository
) : UseCaseSuspend<Nothing?, RandomMeal?> {

    override suspend fun execute(params: Nothing?): RandomMeal? {
        return repository.getRandomMeal()
    }
}