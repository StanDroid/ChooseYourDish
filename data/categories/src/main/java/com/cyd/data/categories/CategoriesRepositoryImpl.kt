package com.cyd.data.categories

import com.cyd.data.network.MealDataSource
import com.cyd.data.categories.mapper.CategoriesMapper
import javax.inject.Inject

class CategoriesRepositoryImpl @Inject constructor(
    private val mealDataSource: MealDataSource,
    private val categoriesMapper: CategoriesMapper,
) : CategoriesRepository {

    override suspend fun getMealCategories() =
        mealDataSource.getMealCategories()?.map { categoriesMapper.map(it) }.orEmpty()
}