package com.cyd.feature.meal_details.usecase

import com.cyd.base.model.Meal
import com.cyd.base.usecase.UseCaseSuspend
import com.cyd.data.meal.MealRepository
import javax.inject.Inject

class RemoveMealFromFavoritesUseCase @Inject constructor(
    private val repository: MealRepository
) : UseCaseSuspend<Meal, Unit> {

    override suspend fun execute(params: Meal) {
        repository.removeFavoriteMeal(params)
    }
}