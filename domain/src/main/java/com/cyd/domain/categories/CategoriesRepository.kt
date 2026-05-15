package com.cyd.domain.categories

import com.cyd.base.model.Category

interface CategoriesRepository {
    suspend fun getMealCategories(): List<Category>
}
