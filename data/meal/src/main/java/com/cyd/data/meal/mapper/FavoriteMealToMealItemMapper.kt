package com.cyd.data.meal.mapper

import com.cyd.base.mapper.Mapper
import com.cyd.base.model.MealItem
import com.cyd.data.db.entity.FavoriteMealEntity
import javax.inject.Inject

class FavoriteMealToMealItemMapper @Inject constructor() : Mapper<FavoriteMealEntity, MealItem> {
    override fun map(param: FavoriteMealEntity): MealItem {
        return MealItem(
            id = param.id,
            name = param.name,
            thumb = param.thumb
        )
    }
}