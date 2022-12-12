package com.example.data.categories

import com.example.chooseyourdish.core.model.Category

interface CategoriesRepository {

    suspend fun getMealCategories(): List<Category>?
}