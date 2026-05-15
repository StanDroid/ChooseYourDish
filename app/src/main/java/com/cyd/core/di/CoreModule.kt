package com.cyd.core.di

import com.cyd.core.CydDispatchersImpl
import com.cyd.domain.CydDispatchers
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface CoreModule {
    @Binds
    fun bindCydDispatchers(cydDispatchers: CydDispatchersImpl): CydDispatchers
//
//    @Provides
//    fun provideCydDispatchers(): CydDispatchers = CydDispatchersImpl()
}
