package com.cyd.data.meal.mapper

import com.cyd.base.mapper.Mapper
import com.cyd.base.mealdb.MealItem
import com.cyd.data.network.model.MealListItemDTO
import javax.inject.Inject

class MealListItemMapper @Inject constructor() : Mapper<MealListItemDTO, MealItem> {

    override fun map(param: MealListItemDTO): MealItem {
        param.let {
            return MealItem(
                id = it.idMeal.orEmpty(),
                name = it.strMeal.orEmpty(),
                thumb = it.strMealThumb.orEmpty()
            )
        }
    }
}