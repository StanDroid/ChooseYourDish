package com.example.chooseyourdish.feature.random_meal.usecase

import com.example.chooseyourdish.core.data.model.mealdb.RandomMeal
import com.example.chooseyourdish.core.data.repository.MealRepository
import com.example.chooseyourdish.core.data.usecase.UseCaseSuspend
import javax.inject.Inject

class GetRandomMealUseCase @Inject constructor(
    private val repository: MealRepository
) : UseCaseSuspend<Nothing?, RandomMeal?> {

    override suspend fun execute(params: Nothing?): RandomMeal? {
        return repository.getRandomMeal().meals.firstOrNull()?.let {
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