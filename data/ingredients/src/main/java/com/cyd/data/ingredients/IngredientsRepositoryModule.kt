package com.cyd.data.ingredients

import com.cyd.base.CydDispatchers
import com.cyd.data.ingredients.mapper.IngredientsMapper
import com.cyd.data.network.MealDataSource
import com.cyd.domain.ingredients.IngredientsRepository
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
        cydDispatchers: CydDispatchers,
    ): IngredientsRepository =
        IngredientsRepositoryImpl(
            mealDataSource,
            ingredientsMapper,
            cydDispatchers,
        )
}
