package com.cyd.data.categories

import com.cyd.base.mealdb.Category
import com.cyd.core.network.MealDataSource
import javax.inject.Inject

class CategoriesRepositoryImpl @Inject constructor(
    private val mealDataSource: MealDataSource
) : CategoriesRepository {

    override suspend fun getMealCategories() = mealDataSource.getMealCategories()?.map {
        Category(
            id = it.id.orEmpty(),
            name = it.name.orEmpty(),
            description = it.description.orEmpty(),
            thumb = it.imageThumb.orEmpty()
        )
    }.orEmpty()
}