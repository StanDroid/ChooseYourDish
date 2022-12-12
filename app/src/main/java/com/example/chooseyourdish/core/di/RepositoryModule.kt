package com.example.chooseyourdish.core.di

import com.example.chooseyourdish.core.data.repository.MealRepository
import com.example.chooseyourdish.core.data.repository.MealRepositoryImpl
import com.example.chooseyourdish.core.network.meal.MealService
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
