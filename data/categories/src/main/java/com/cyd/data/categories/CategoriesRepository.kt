package com.cyd.data.categories

import com.cyd.core.network.model.Category

interface CategoriesRepository {

    suspend fun getMealCategories(): List<Category>?
}