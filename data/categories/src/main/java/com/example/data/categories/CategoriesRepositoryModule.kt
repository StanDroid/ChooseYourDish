package com.example.data.categories

import com.example.chooseyourdish.core.MealDataSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object CategoriesRepositoryModule {

    @Provides
    fun provideCategoriesRepository(
        mealDataSource: MealDataSource
    ): CategoriesRepository = CategoriesRepositoryImpl(mealDataSource)
}
