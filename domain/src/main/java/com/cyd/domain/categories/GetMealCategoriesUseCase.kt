package com.cyd.domain.categories

import com.cyd.base.model.Category
import com.cyd.base.usecase.UseCase
import javax.inject.Inject

class GetMealCategoriesUseCase
@Inject
constructor(
    private val repository: CategoriesRepository,
) : UseCase<Nothing?, List<Category>> {
    override suspend fun execute(params: Nothing?): List<Category> = repository.getMealCategories()
}
