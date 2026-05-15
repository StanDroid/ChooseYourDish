package com.cyd.data.categories

import com.cyd.base.CydDispatchers
import com.cyd.data.categories.mapper.CategoriesMapper
import com.cyd.data.network.MealDataSource
import com.cyd.domain.categories.CategoriesRepository
import kotlinx.coroutines.withContext
import javax.inject.Inject

class CategoriesRepositoryImpl
    @Inject
    constructor(
        private val mealDataSource: MealDataSource,
        private val categoriesMapper: CategoriesMapper,
        private val cydDispatchers: CydDispatchers,
    ) : CategoriesRepository {
        override suspend fun getMealCategories() =
            withContext(cydDispatchers.io) {
                mealDataSource
                    .getMealCategories()
                    ?.map {
                        categoriesMapper.map(it)
                    }.orEmpty()
            }
    }
