package com.cyd.search.usecase

import com.cyd.base.model.Ingredient
import com.cyd.base.usecase.UseCase
import com.cyd.data.ingredients.IngredientsRepository
import javax.inject.Inject

class GetAllIngredientsUseCase
@Inject
constructor(
    private val repository: IngredientsRepository,
) : UseCase<Nothing?, List<Ingredient>> {
    override suspend fun execute(params: Nothing?): List<Ingredient> = repository.getIngredients()
    }
