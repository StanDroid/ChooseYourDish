package com.example.composetraining.feature.random_meal.usecase

import com.example.composetraining.core.data.model.mealdb.RandomMeal
import com.example.composetraining.core.data.repository.MealRepository
import com.example.composetraining.core.data.usecase.UseCase
import io.reactivex.Single
import javax.inject.Inject

class GetRandomMealUseCase @Inject constructor(
    private val repository: MealRepository
) : UseCase<Nothing?, Single<RandomMeal>> {

    override fun execute(params: Nothing?): Single<RandomMeal> {
        return repository.getRandomMeal()
            .map { list ->
                list.meals.firstOrNull()?.let {
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
}