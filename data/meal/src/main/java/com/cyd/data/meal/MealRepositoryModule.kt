package com.cyd.data.meal

import com.cyd.data.network.MealDataSource
import com.cyd.data.meal.mapper.MealDetailsMapper
import com.cyd.data.meal.mapper.MealListItemMapper
import com.cyd.data.meal.mapper.RandomMealMapper
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object MealRepositoryModule {

    @Provides
    fun provideMealRepository(
        mealDataSource: MealDataSource,
        randomMealMapper: RandomMealMapper,
        mealListItemMapper: MealListItemMapper,
        mealDetailsMapper: MealDetailsMapper,
    ): MealRepository =
        MealRepositoryImpl(
            mealDataSource,
            randomMealMapper,
            mealListItemMapper,
            mealDetailsMapper
        )
}
