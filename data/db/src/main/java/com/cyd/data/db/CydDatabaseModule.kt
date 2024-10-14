package com.cyd.data.db

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object CydDatabaseModule {
    @Provides
    fun provideDatabaseModule(context: Context) = Room.databaseBuilder(
        context.applicationContext, CydDatabase::class.java, "cyd_database"
    ).build()
}