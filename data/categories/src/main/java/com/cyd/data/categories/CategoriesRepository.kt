package com.cyd.data.categories

import com.cyd.base.model.Category

interface CategoriesRepository {

    suspend fun getMealCategories(): List<Category>
}