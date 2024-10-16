package com.cyd.data.meal.mapper

import com.cyd.base.mapper.Mapper
import com.cyd.base.model.MealItem
import com.cyd.data.db.entity.FavoriteMealEntity
import javax.inject.Inject

class MealItemToFavoriteMealMapper @Inject constructor() : Mapper<MealItem, FavoriteMealEntity> {

    override fun map(param: MealItem) =
        FavoriteMealEntity(
            id = param.id,
            name = param.name,
            thumb = param.thumb
        )
}