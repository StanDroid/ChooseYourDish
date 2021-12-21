package com.example.composetraining.core.di

import com.example.composetraining.core.data.repository.MemeRepository
import com.example.composetraining.core.data.repository.MemeRepositoryImpl
import com.example.composetraining.core.network.MemeService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.components.ViewModelComponent
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(ViewModelComponent::class)
object NetworkModule {

//    @Singleton
    @Provides
    fun provideMemeService(): MemeService {
        return Retrofit.Builder()
            .baseUrl("http://alpha-meme-maker.herokuapp.com")
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
            .create(MemeService::class.java)
    }

//    @Singleton
    @Provides
    fun provideMemeRepository(
        memeService: MemeService
    ): MemeRepository {
        return MemeRepositoryImpl(memeService)
    }
}