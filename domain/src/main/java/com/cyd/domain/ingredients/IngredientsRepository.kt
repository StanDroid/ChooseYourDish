package com.cyd.domain.ingredients

import com.cyd.base.model.Ingredient

interface IngredientsRepository {
    suspend fun getIngredients(): List<Ingredient>
}
