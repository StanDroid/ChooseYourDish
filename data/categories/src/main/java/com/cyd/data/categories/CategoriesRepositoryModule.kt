package com.cyd.data.categories

import com.cyd.data.network.MealDataSource
import com.cyd.data.categories.mapper.CategoriesMapper
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object CategoriesRepositoryModule {

    @Provides
    fun provideCategoriesRepository(
        mealDataSource: MealDataSource,
        categoriesMapper: CategoriesMapper,
    ): CategoriesRepository = CategoriesRepositoryImpl(
        mealDataSource,
        categoriesMapper
    )
}
