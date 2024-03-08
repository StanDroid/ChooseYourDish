package com.cyd.data.categories

import com.cyd.base.mealdb.Category

interface CategoriesRepository {

    suspend fun getMealCategories(): List<Category>
}