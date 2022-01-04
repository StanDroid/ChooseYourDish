package com.example.composetraining.core.di

import com.example.composetraining.core.data.repository.MealRepository
import com.example.composetraining.core.data.repository.MealRepositoryImpl
import com.example.composetraining.core.data.repository.MemeRepository
import com.example.composetraining.core.data.repository.MemeRepositoryImpl
import com.example.composetraining.core.network.memes.MemeService
import com.example.composetraining.core.network.meal.MealService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

@Module
@InstallIn(ViewModelComponent::class)
object NetworkModule {

    @Provides
    fun provideMemeService(): MemeService {
        return Retrofit.Builder()
            .baseUrl("http://alpha-meme-maker.herokuapp.com")
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
            .create(MemeService::class.java)
    }

    @Provides
    fun provideMealService(): MealService {
        return Retrofit.Builder()
            .baseUrl("https://www.themealdb.com/api/json/v1/1/")
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
            .create(MealService::class.java)
    }

    @Provides
    fun provideMemeRepository(
        memeService: MemeService
    ): MemeRepository {
        return MemeRepositoryImpl(memeService)
    }

    @Provides
    fun provideMealRepository(
        mealService: MealService
    ): MealRepository {
        return MealRepositoryImpl(mealService)
    }
}