package com.example.chooseyourdish.feature.meals_by_category.usecase

import com.example.chooseyourdish.core.data.model.mealdb.MealItem
import com.example.chooseyourdish.core.data.repository.MealRepository
import com.example.chooseyourdish.core.data.usecase.UseCaseSuspend
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