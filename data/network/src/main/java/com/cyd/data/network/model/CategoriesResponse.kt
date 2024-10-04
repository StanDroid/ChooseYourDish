package com.cyd.data.network.model

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

@Keep
data class CategoriesResponse(
    val categories: List<CategoryDTO>?
)

@Keep
data class CategoryDTO(
    @SerializedName("idCategory") val id: String?,
    @SerializedName("strCategory") val name: String?,
    @SerializedName("strCategoryDescription") val description: String?,
    @SerializedName("strCategoryThumb") val imageThumb: String?
)