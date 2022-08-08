package com.example.composetraining.feature.meals_by_category.usecase

import com.example.composetraining.core.data.model.mealdb.MealItem
import com.example.composetraining.core.data.repository.MealRepository
import com.example.composetraining.core.data.usecase.UseCaseSuspend
import javax.inject.Inject

class GetMealListUseCase @Inject constructor(
    private val repository: MealRepository
) : UseCaseSuspend<String, List<MealItem>?> {

    override suspend fun execute(params: String): List<MealItem>? {
        return repository.getMealsByCategory(params).meals?.map {
            MealItem(
                id = it.idMeal.orEmpty(),
                name = it.strMeal.orEmpty(),
                thumb = it.strMealThumb.orEmpty()
            )
        }
    }
}