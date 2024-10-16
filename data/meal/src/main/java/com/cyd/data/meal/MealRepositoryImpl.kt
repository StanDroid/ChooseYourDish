package com.cyd.data.meal

import com.cyd.base.model.MealItem
import com.cyd.data.db.FavoriteMealDao
import com.cyd.data.db.entity.FavoriteMealEntity
import com.cyd.data.meal.mapper.FavoriteMealToMealItemMapper
import com.cyd.data.meal.mapper.MealDetailsMapper
import com.cyd.data.meal.mapper.MealItemToFavoriteMealMapper
import com.cyd.data.meal.mapper.MealListItemMapper
import com.cyd.data.meal.mapper.RandomMealMapper
import com.cyd.data.network.MealDataSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class MealRepositoryImpl @Inject constructor(
    private val mealDataSource: MealDataSource,
    private val randomMealMapper: RandomMealMapper,
    private val mealListItemMapper: MealListItemMapper,
    private val mealDetailsMapper: MealDetailsMapper,
    private val favoriteMealDao: FavoriteMealDao,
    private val favoriteMealToMealItemMapper: FavoriteMealToMealItemMapper,
    private val mealItemToFavoriteMealMapper: MealItemToFavoriteMealMapper,
) : MealRepository {

    override suspend fun getRandomMeal() =
        mealDataSource.getRandomMeal()?.let { randomMealMapper.map(it) }

    override suspend fun getMealsByCategory(name: String) =
        mealDataSource.getMealsByCategory(name)?.map {
            mealListItemMapper.map(it)
        }.orEmpty()

    override suspend fun getMealDetails(id: String) =
        mealDataSource.getMealDetails(id)?.let { mealDetailsMapper.map(it) }

    override suspend fun getMealsByMainIngredient(name: String) =
        mealDataSource.getMealsByMainIngredient(name)?.map {
            mealListItemMapper.map(it)
        }.orEmpty()

    override suspend fun getFavoritesMeals(): Flow<List<MealItem>> {
        return favoriteMealDao.getFavoriteMeals().map {
            favoriteMealToMealItemMapper.map(it)
        }
    }

    override suspend fun getFavoritesMealIds(): List<String> {
        return favoriteMealDao.getFavoriteMealIds() ?: emptyList()
    }

    override suspend fun insertFavoriteMeal(meal: MealItem) {
        val favoriteMeal: FavoriteMealEntity = mealItemToFavoriteMealMapper.map(meal)
        favoriteMealDao.insertFavoriteMeal(favoriteMeal)
    }

    override suspend fun removeFavoriteMeal(meal: MealItem) {
        favoriteMealDao.removeFavoriteMeal(mealItemToFavoriteMealMapper.map(meal))
    }
}