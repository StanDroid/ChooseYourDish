package com.example.composetraining.core.di

import com.example.composetraining.core.data.repository.MealRepository
import com.example.composetraining.core.data.repository.MealRepositoryImpl
import com.example.composetraining.core.network.meal.MealService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
object RepositoryModule {

    @Provides
    fun provideMealRepository(
        mealService: MealService
    ): MealRepository = MealRepositoryImpl(mealService)
}
