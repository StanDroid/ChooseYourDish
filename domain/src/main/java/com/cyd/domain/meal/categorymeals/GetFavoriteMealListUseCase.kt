package com.cyd.domain.meal.categorymeals

import com.cyd.base.model.MealItem
import com.cyd.base.usecase.UseCase
import com.cyd.domain.meal.MealRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetFavoriteMealListUseCase
    @Inject
    constructor(
        private val repository: MealRepository,
    ) : UseCase<Nothing?, Flow<List<MealItem>>> {
        override suspend fun execute(params: Nothing?): Flow<List<MealItem>> = repository.getFavoritesMeals()
    }
