package com.cyd.feature.category_meals.usecase

import com.cyd.base.model.MealItem
import com.cyd.base.usecase.UseCaseSuspend
import com.cyd.data.meal.MealRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetFavoriteMealListUseCase @Inject constructor(
    private val repository: MealRepository
) : UseCaseSuspend<Nothing?, Flow<List<MealItem>>> {

    override suspend fun execute(params: Nothing?): Flow<List<MealItem>> {
        return repository.getFavoritesMeals()
    }
}