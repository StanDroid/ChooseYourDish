package com.cyd.core.network.model

data class CategoriesResponse(
    val categories: List<Category>?
)

data class Category(
    val idCategory: String?,
    val strCategory: String?,
    val strCategoryDescription: String?,
    val strCategoryThumb: String?
)