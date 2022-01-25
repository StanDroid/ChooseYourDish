package com.example.composetraining.feature.meal_categories.usecase

import com.example.composetraining.core.data.model.mealdb.Category
import com.example.composetraining.core.data.repository.MealRepository
import com.example.composetraining.core.data.usecase.UseCase
import io.reactivex.Single
import javax.inject.Inject

class GetMealCategoriesUseCase @Inject constructor(
    private val repository: MealRepository
) : UseCase<Nothing?, Single<List<Category>>> {

    override fun execute(params: Nothing?): Single<List<Category>> {
        return repository.getMealCategories()
            .toObservable()
            .flatMapIterable { it.categories }
            .map {
                Category(
                    id = it.idCategory.orEmpty(),
                    name = it.strCategory.orEmpty(),
                    description = it.strCategoryDescription.orEmpty(),
                    thumb = it.strCategoryThumb.orEmpty()
                )
            }
            .toList()
    }
}