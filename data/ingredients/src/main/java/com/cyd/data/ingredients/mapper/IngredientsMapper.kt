package com.cyd.data.ingredients.mapper

import com.cyd.base.mapper.Mapper
import com.cyd.base.model.Ingredient
import com.cyd.data.network.model.IngredientDTO
import javax.inject.Inject

class IngredientsMapper @Inject constructor() : Mapper<IngredientDTO, Ingredient> {

    override fun map(param: IngredientDTO): Ingredient {
        param.let {
            return Ingredient(
                id = it.id.orEmpty(),
                name = it.name.orEmpty(),
                description = it.description.orEmpty(),
            )
        }
    }
}