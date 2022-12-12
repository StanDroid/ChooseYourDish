package com.example.chooseyourdish.feature.meal_categories.usecase

import com.example.chooseyourdish.core.data.model.mealdb.Category
import com.example.chooseyourdish.core.data.repository.MealRepository
import com.example.chooseyourdish.core.data.usecase.UseCaseSuspend
import javax.inject.Inject

class GetMealCategoriesUseCase @Inject constructor(
    private val repository: MealRepository
) : UseCaseSuspend<Nothing?, List<Category>> {

    override suspend fun execute(params: Nothing?): List<Category> {
        return repository.getMealCategories().categories
            .map {
                Category(
                    id = it.idCategory.orEmpty(),
                    name = it.strCategory.orEmpty(),
                    description = it.strCategoryDescription.orEmpty(),
                    thumb = it.strCategoryThumb.orEmpty()
                )
            }
    }
}