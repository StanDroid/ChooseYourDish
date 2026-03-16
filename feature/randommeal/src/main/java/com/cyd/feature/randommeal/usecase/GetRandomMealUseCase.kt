package com.cyd.feature.randommeal.usecase

import com.cyd.base.model.RandomMeal
import com.cyd.base.usecase.UseCase
import com.cyd.data.meal.MealRepository
import javax.inject.Inject

class GetRandomMealUseCase
@Inject
constructor(
    private val repository: MealRepository,
) : UseCase<Nothing?, RandomMeal?> {
    override suspend fun execute(params: Nothing?): RandomMeal? = repository.getRandomMeal()
}
