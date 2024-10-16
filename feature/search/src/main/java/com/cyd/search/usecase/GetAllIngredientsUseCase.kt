package com.cyd.search.usecase

import com.cyd.base.model.Ingredient
import com.cyd.base.usecase.UseCaseSuspend
import com.cyd.data.ingredients.IngredientsRepository
import javax.inject.Inject

class GetAllIngredientsUseCase @Inject constructor(
    private val repository: IngredientsRepository
) : UseCaseSuspend<Nothing?, List<Ingredient>> {

    override suspend fun execute(params: Nothing?): List<Ingredient> {
        return repository.getIngredients()
    }
}