package com.cyd.data.ingredients

import com.cyd.data.ingredients.mapper.IngredientsMapper
import com.cyd.data.network.MealDataSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object IngredientsRepositoryModule {

    @Provides
    fun provideIngredientsRepository(
        mealDataSource: MealDataSource,
        ingredientsMapper: IngredientsMapper,
    ): IngredientsRepository = IngredientsRepositoryImpl(
        mealDataSource,
        ingredientsMapper
    )
}
