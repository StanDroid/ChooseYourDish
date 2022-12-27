package com.cyd.data.categories

import com.cyd.core.network.model.CategoryDTO

interface CategoriesRepository {

    suspend fun getMealCategories(): List<CategoryDTO>?
}