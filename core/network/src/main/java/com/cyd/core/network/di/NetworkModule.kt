package com.cyd.core.network.di

import com.cyd.core.network.MealDataSource
import com.cyd.core.network.RetrofitMealNetwork
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface NetworkModule {

    @Binds
    fun bindRetrofitMealNetwork(retrofitMealNetwork: RetrofitMealNetwork): MealDataSource

}