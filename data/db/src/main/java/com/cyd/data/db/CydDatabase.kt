package com.cyd.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.cyd.data.db.converter.MealIngredientListConverter
import com.cyd.data.db.entity.FavoriteMealEntity

@Database(entities = [FavoriteMealEntity::class], version = 1)
@TypeConverters(MealIngredientListConverter::class)
abstract class CydDatabase : RoomDatabase() {
    abstract fun favoriteMealsDao(): FavoriteMealDao
}