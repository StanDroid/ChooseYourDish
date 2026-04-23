package com.cyd.feature.categorymeals.usecase

import com.cyd.base.model.MealItem
import com.cyd.base.usecase.UseCase
import com.cyd.data.meal.MealRepository
import javax.inject.Inject

class GetMealListUseCase
@Inject
constructor(
    private val repository: MealRepository,
) : UseCase<String, List<MealItem>?> {
    override suspend fun execute(params: String): List<MealItem> = repository.getMealsByCategory(params)
}
