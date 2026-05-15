package com.cyd.data.ingredients

import com.cyd.data.ingredients.mapper.IngredientsMapper
import com.cyd.data.network.MealDataSource
import com.cyd.domain.CydDispatchers
import com.cyd.domain.ingredients.IngredientsRepository
import kotlinx.coroutines.withContext
import javax.inject.Inject

class IngredientsRepositoryImpl
    @Inject
    constructor(
        private val mealDataSource: MealDataSource,
        private val ingredientsMapper: IngredientsMapper,
        private val cydDispatchers: CydDispatchers,
    ) : IngredientsRepository {
        override suspend fun getIngredients() =
            withContext(cydDispatchers.io) {
                mealDataSource.getIngredients()?.map { ingredientsMapper.map(it) }.orEmpty()
            }
    }
