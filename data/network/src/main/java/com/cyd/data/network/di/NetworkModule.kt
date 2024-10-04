package com.cyd.data.network.di

import com.cyd.data.network.KtorMealDataSourceImpl
import com.cyd.data.network.MealDataSource
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface NetworkModule {

    @Binds
    fun bindKtorMealDataSource(mealDataSource: KtorMealDataSourceImpl): MealDataSource

}