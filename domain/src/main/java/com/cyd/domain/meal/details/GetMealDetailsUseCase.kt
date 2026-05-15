package com.cyd.domain.meal.details

import com.cyd.base.CydDispatchers
import com.cyd.base.model.Meal
import com.cyd.base.usecase.UseCase
import com.cyd.domain.meal.MealRepository
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.withContext
import javax.inject.Inject

class GetMealDetailsUseCase
    @Inject
    constructor(
        private val repository: MealRepository,
        private val cydDispatchers: CydDispatchers,
    ) : UseCase<String, Meal> {
        override suspend fun execute(params: String): Meal =
            withContext(cydDispatchers.io) {
                coroutineScope {
                    val isFavoriteTask = async { repository.getFavoritesMealIds().any { it == params } }
                    val detailMealTask = async { repository.getMealDetails(params) }
                    val mealDetails = detailMealTask.await() ?: throw NoSuchElementException()
                    val isFavorite = isFavoriteTask.await()
                    if (isFavorite) {
                        mealDetails.copy(isFavorite = isFavorite)
                    } else {
                        mealDetails
                    }
                }
            }
    }
