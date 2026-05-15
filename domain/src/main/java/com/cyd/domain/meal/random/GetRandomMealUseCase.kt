package com.cyd.domain.meal.random

import com.cyd.base.model.RandomMeal
import com.cyd.base.usecase.UseCase
import com.cyd.domain.meal.MealRepository
import javax.inject.Inject

class GetRandomMealUseCase
    @Inject
    constructor(
        private val repository: MealRepository,
    ) : UseCase<Nothing?, RandomMeal?> {
        override suspend fun execute(params: Nothing?): RandomMeal? = repository.getRandomMeal()
    }
