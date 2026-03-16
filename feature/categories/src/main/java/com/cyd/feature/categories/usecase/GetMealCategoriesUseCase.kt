package com.cyd.feature.categories.usecase

import com.cyd.base.model.Category
import com.cyd.base.usecase.UseCase
import com.cyd.data.categories.CategoriesRepository
import javax.inject.Inject

class GetMealCategoriesUseCase
@Inject
constructor(
    private val repository: CategoriesRepository,
) : UseCase<Nothing?, List<Category>> {
    override suspend fun execute(params: Nothing?): List<Category> = repository.getMealCategories()
    }
