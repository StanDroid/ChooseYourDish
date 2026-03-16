package com.cyd.feature.mealdetails.usecase

import com.cyd.base.model.Meal
import com.cyd.base.usecase.UseCase
import com.cyd.data.meal.MealRepository
import javax.inject.Inject

class RemoveMealFromFavoritesUseCase
@Inject
constructor(
    private val repository: MealRepository,
) : UseCase<Meal, Unit> {
    override suspend fun execute(params: Meal) {
        repository.removeFavoriteMeal(params.toMealItem())
    }
}
