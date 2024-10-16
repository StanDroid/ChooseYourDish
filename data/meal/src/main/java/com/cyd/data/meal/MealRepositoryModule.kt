package com.cyd.data.meal

import com.cyd.data.db.FavoriteMealDao
import com.cyd.data.meal.mapper.FavoriteMealToMealItemMapper
import com.cyd.data.meal.mapper.MealDetailsMapper
import com.cyd.data.meal.mapper.MealItemToFavoriteMealMapper
import com.cyd.data.meal.mapper.MealListItemMapper
import com.cyd.data.meal.mapper.RandomMealMapper
import com.cyd.data.network.MealDataSource
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
        favoriteMealDao: FavoriteMealDao,
        mealItemToFavoriteMealMapper: MealItemToFavoriteMealMapper,
        favoriteMealToMealItemMapper: FavoriteMealToMealItemMapper
    ): MealRepository =
        MealRepositoryImpl(
            mealDataSource,
            randomMealMapper,
            mealListItemMapper,
            mealDetailsMapper,
            favoriteMealDao,
            favoriteMealToMealItemMapper,
            mealItemToFavoriteMealMapper
        )
}
