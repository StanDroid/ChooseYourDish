package com.cyd.data.db

import android.app.Application
import android.content.Context
import androidx.room.Room
import com.cyd.base.utils.DATABASE_NAME
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object CydDatabaseModule {

    @Provides
    fun provideContext(application: Application): Context {
        return application.applicationContext
    }

    @Provides
    @Singleton
    fun provideDatabaseModule(context: Context) = Room.databaseBuilder(
        context.applicationContext, CydDatabase::class.java, DATABASE_NAME
    ).build()

    @Provides
    @Singleton
    fun provideFavoriteMealDao(appDatabase: CydDatabase): FavoriteMealDao {
        return appDatabase.favoriteMealsDao()
    }
}