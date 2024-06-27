package com.cyd.feature.category_meals.usecase

import com.cyd.base.model.MealItem
import com.cyd.base.usecase.UseCaseSuspend
import com.cyd.data.meal.MealRepository
import javax.inject.Inject

class GetMealListUseCase @Inject constructor(
    private val repository: MealRepository
) : UseCaseSuspend<String, List<MealItem>?> {

    override suspend fun execute(params: String): List<MealItem> {
        return repository.getMealsByCategory(params)
    }
}