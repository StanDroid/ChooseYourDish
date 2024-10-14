package com.cyd.feature.category_meals.usecase

import com.cyd.base.model.MealItem
import com.cyd.base.usecase.UseCaseSuspend
import com.cyd.data.meal.MealRepository
import javax.inject.Inject

class GetFavoriteMealListUseCase @Inject constructor(
    private val repository: MealRepository
) : UseCaseSuspend<Nothing?, List<MealItem>?> {

    override suspend fun execute(params: Nothing?): List<MealItem> {
        return repository.getFavoritesMeals().map { it.toMealItem() }
    }
}