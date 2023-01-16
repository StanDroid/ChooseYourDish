package com.cyd.data.meal

import com.cyd.core.network.MealDataSource
import com.cyd.data.meal.mapper.MealDetailsMapper
import com.cyd.data.meal.mapper.MealListItemMapper
import com.cyd.data.meal.mapper.RandomMealMapper
import javax.inject.Inject

class MealRepositoryImpl @Inject constructor(
    private val mealDataSource: MealDataSource,
    private val randomMealMapper: RandomMealMapper,
    private val mealListItemMapper: MealListItemMapper,
    private val mealDetailsMapper: MealDetailsMapper,
) : MealRepository {

    override suspend fun getRandomMeal() =
        mealDataSource.getRandomMeal()?.let { randomMealMapper.map(it) }

    override suspend fun getMealsByCategory(name: String) =
        mealDataSource.getMealsByCategory(name)?.map {
            mealListItemMapper.map(it)
        }.orEmpty()

    override suspend fun getMealDetails(id: String) =
        mealDataSource.getMealDetails(id)?.let { mealDetailsMapper.map(it) }
}