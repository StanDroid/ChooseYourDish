package com.cyd.data.network.model

import com.google.gson.annotations.SerializedName

data class CategoriesResponse(
    val categories: List<CategoryDTO>?
)

data class CategoryDTO(
    @SerializedName("idCategory") val id: String?,
    @SerializedName("strCategory") val name: String?,
    @SerializedName("strCategoryDescription") val description: String?,
    @SerializedName("strCategoryThumb") val imageThumb: String?
)