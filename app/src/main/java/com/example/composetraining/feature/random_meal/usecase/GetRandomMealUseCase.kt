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
                        idMeal = it.idMeal,
                        strArea = it.strArea,
                        strMeal = it.strMeal,
                        strCategory = it.strCategory,
                        strInstructions = it.strInstructions,
                        strMealThumb = it.strMealThumb,
                        strSource = it.strSource,
                        strYoutube = it.strYoutube
                    )
                }
            }
    }
}