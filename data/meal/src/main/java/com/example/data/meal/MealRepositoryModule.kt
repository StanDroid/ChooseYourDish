package com.example.data.meal

import com.example.chooseyourdish.core.MealDataSource
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
