package com.cyd.data.ingredients

import com.cyd.data.ingredients.mapper.IngredientsMapper
import com.cyd.data.network.MealDataSource
import javax.inject.Inject

class IngredientsRepositoryImpl @Inject constructor(
    private val mealDataSource: MealDataSource,
    private val ingredientsMapper: IngredientsMapper,
) : IngredientsRepository {

    override suspend fun getIngredients() =
        mealDataSource.getIngredients()?.map { ingredientsMapper.map(it) }.orEmpty()
}