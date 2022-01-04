package com.example.composetraining.core.di

import com.example.composetraining.core.data.repository.MealRepository
import com.example.composetraining.core.data.repository.MealRepositoryImpl
import com.example.composetraining.core.data.repository.MemeRepository
import com.example.composetraining.core.data.repository.MemeRepositoryImpl
import com.example.composetraining.core.network.meal.MealService
import com.example.composetraining.core.network.memes.MemeService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
object RepositoryModule {

    @Provides
    fun provideMemeRepository(
        memeService: MemeService
    ): MemeRepository = MemeRepositoryImpl(memeService)

    @Provides
    fun provideMealRepository(
        mealService: MealService
    ): MealRepository = MealRepositoryImpl(mealService)
}
