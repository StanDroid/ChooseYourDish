package com.cyd.domain.meal.categorymeals

import com.cyd.base.model.MealItem
import com.cyd.base.usecase.UseCase
import com.cyd.domain.meal.MealRepository
import javax.inject.Inject

class GetMealListUseCase
@Inject
constructor(
    private val repository: MealRepository,
) : UseCase<String, List<MealItem>?> {
    override suspend fun execute(params: String): List<MealItem> = repository.getMealsByCategory(params)
}
