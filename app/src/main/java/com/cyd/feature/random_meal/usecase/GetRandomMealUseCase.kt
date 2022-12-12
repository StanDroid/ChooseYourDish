package com.cyd.feature.random_meal.usecase

import com.cyd.core.data.model.mealdb.RandomMeal
import com.cyd.core.data.usecase.UseCaseSuspend
import com.cyd.data.meal.MealRepository
import javax.inject.Inject

class GetRandomMealUseCase @Inject constructor(
    private val repository: MealRepository
) : UseCaseSuspend<Nothing?, RandomMeal?> {

    override suspend fun execute(params: Nothing?): RandomMeal? {
        return repository.getRandomMeal()?.let {
            RandomMeal(
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