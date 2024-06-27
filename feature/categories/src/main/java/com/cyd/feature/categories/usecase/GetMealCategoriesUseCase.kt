package com.cyd.feature.categories.usecase

import com.cyd.base.model.Category
import com.cyd.base.usecase.UseCaseSuspend
import com.cyd.data.categories.CategoriesRepository
import javax.inject.Inject

class GetMealCategoriesUseCase @Inject constructor(
    private val repository: CategoriesRepository
) : UseCaseSuspend<Nothing?, List<Category>> {

    override suspend fun execute(params: Nothing?): List<Category> {
        return repository.getMealCategories()
    }
}