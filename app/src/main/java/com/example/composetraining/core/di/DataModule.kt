package com.example.composetraining.core.di

import com.example.composetraining.core.data.repository.MemeRepository
import com.example.composetraining.core.data.repository.MemeRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent


//@Module
//@InstallIn(ActivityComponent::class)
//abstract class DataModule {
//
//    @Binds
//    abstract fun provideMemeRepository(
//        repositoryImpl: MemeRepositoryImpl
//    ): MemeRepository
//}