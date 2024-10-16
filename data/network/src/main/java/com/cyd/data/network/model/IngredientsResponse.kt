package com.cyd.data.network.model

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

@Keep
data class IngredientsResponse(
    @SerializedName("meals")
    val ingredients: List<IngredientDTO>?
)

@Keep
data class IngredientDTO(
    @SerializedName("idIngredient") val id: String?,
    @SerializedName("strIngredient") val name: String?,
    @SerializedName("strDescription") val description: String?,
)