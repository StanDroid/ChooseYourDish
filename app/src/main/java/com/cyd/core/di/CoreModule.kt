package com.cyd.core.di

import com.cyd.base.CydDispatchers
import com.cyd.core.CydDispatchersImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface CoreModule {
    @Binds
    fun bindCydDispatchers(cydDispatchers: CydDispatchersImpl): CydDispatchers
}
