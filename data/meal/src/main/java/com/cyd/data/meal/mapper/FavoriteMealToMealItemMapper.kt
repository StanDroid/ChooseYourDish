package com.cyd.data.meal.mapper

import com.cyd.base.mapper.Mapper
import com.cyd.base.model.Meal
import com.cyd.data.db.entity.FavoriteMealEntity
import javax.inject.Inject

class FavoriteMealToMealItemMapper @Inject constructor() : Mapper<FavoriteMealEntity, Meal> {
    override fun map(param: FavoriteMealEntity): Meal {
        return Meal(
            id = param.id,
            dateModified = param.dateModified,
            area = param.area,
            category = param.category,
            creativeCommonsConfirmed = param.creativeCommonsConfirmed,
            drinkAlternate = param.drinkAlternate,
            imageSource = param.imageSource,
            mealIngredients = param.mealIngredients,
            instructions = param.instructions,
            meal = param.meal,
            mealThumb = param.mealThumb,
            source = param.source,
            tags = param.tags,
            youtube = param.youtube,

            )
    }
}