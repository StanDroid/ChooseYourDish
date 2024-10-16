package com.cyd.data.ingredients

import com.cyd.base.model.Ingredient

interface IngredientsRepository {
    suspend fun getIngredients(): List<Ingredient>
}