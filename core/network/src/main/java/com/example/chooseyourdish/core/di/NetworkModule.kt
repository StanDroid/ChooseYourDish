package com.example.chooseyourdish.core.di

import com.example.chooseyourdish.core.MealDataSource
import com.example.chooseyourdish.core.RetrofitMealNetwork
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface NetworkModule {

    @Binds
    fun RetrofitMealNetwork.binds(): MealDataSource

}