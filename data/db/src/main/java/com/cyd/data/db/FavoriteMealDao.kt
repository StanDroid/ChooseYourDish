package com.cyd.data.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.cyd.data.db.entity.FavoriteMealEntity

@Dao
interface FavoriteMealDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFavoriteMeal(meal: FavoriteMealEntity)

    @Query("SELECT * FROM favorite_meals")
    suspend fun getFavoriteMeals(): List<FavoriteMealEntity>?

    @Delete
    suspend fun removeFavoriteMeal(meal: FavoriteMealEntity)
}