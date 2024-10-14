package com.cyd.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.cyd.data.db.entity.FavoriteMealEntity

@Database(entities = [FavoriteMealEntity::class], version = 1)
abstract class CydDatabase : RoomDatabase() {
    abstract fun favoriteMealsDao(): FavoriteMealDao
}