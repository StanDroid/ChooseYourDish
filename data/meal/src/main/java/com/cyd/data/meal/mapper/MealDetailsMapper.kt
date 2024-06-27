package com.cyd.data.meal.mapper

import com.cyd.base.mapper.Mapper
import com.cyd.base.model.Ingredient
import com.cyd.base.model.Meal
import com.cyd.data.network.model.MealDetailsDTO
import javax.inject.Inject

class MealDetailsMapper @Inject constructor() : Mapper<MealDetailsDTO, Meal> {

    override fun map(param: MealDetailsDTO): Meal {
        val ingredients = mutableListOf<Ingredient>()
        for (i in 1..20) {
            val ingredientName = param.getIngredient(i)
            val measureName = param.getMeasure(i)
            if (!ingredientName.isNullOrEmpty()) {
                ingredients.add(
                    Ingredient(
                        name = ingredientName,
                        measure = measureName
                    )
                )
            }
        }
        return Meal(
            id = param.idMeal,
            dateModified = param.dateModified,
            area = param.strArea,
            category = param.strCategory,
            creativeCommonsConfirmed = param.strCreativeCommonsConfirmed,
            drinkAlternate = param.strDrinkAlternate,
            imageSource = param.strImageSource,
            ingredients = ingredients.toList(),
            instructions = param.strInstructions,
            meal = param.strMeal,
            mealThumb = param.strMealThumb,
            source = param.strSource,
            tags = param.strTags,
            youtube = param.strYoutube
        )

    }
}