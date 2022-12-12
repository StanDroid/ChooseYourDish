package com.cyd.data.meal

import com.cyd.core.network.MealDataSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object MealRepositoryModule {

    @Provides
    fun provideMealRepository(
        mealDataSource: MealDataSource
    ): MealRepository =
        MealRepositoryImpl(mealDataSource)
}
