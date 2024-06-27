package com.cyd.data.meal.mapper

import com.cyd.base.mapper.Mapper
import com.cyd.base.model.RandomMeal
import com.cyd.data.network.model.RandomMealDTO
import javax.inject.Inject

class RandomMealMapper @Inject constructor() : Mapper<RandomMealDTO, RandomMeal> {

    override fun map(param: RandomMealDTO): RandomMeal {
        param.let {
            return RandomMeal(
                idMeal = it.idMeal.orEmpty(),
                strArea = it.strArea.orEmpty(),
                strMeal = it.strMeal.orEmpty(),
                strCategory = it.strCategory.orEmpty(),
                strInstructions = it.strInstructions.orEmpty(),
                strMealThumb = it.strMealThumb.orEmpty(),
                strSource = it.strSource.orEmpty(),
                strYoutube = it.strYoutube.orEmpty()
            )
        }
    }
}