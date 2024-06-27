package com.cyd.data.categories.mapper

import com.cyd.base.mapper.Mapper
import com.cyd.base.model.Category
import com.cyd.data.network.model.CategoryDTO
import javax.inject.Inject

class CategoriesMapper @Inject constructor() : Mapper<CategoryDTO, Category> {

    override fun map(param: CategoryDTO): Category {
        param.let {
            return Category(
                id = it.id.orEmpty(),
                name = it.name.orEmpty(),
                description = it.description.orEmpty(),
                thumb = it.imageThumb.orEmpty()
            )
        }
    }
}