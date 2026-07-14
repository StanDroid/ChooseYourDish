package com.cyd.data.categories.mapper

import com.cyd.base.model.Category
import com.cyd.data.network.model.CategoryDTO
import org.junit.Assert.assertEquals
import org.junit.Test

class CategoriesMapperTest {
    private val mapper = CategoriesMapper()

    @Test
    fun `map with all fields populated returns correct Category`() {
        val dto =
            CategoryDTO(
                id = "1",
                name = "Beef",
                description = "Beef is the culinary name for meat from cattle",
                imageThumb = "https://example.com/beef.png",
            )

        val result = mapper.map(dto)

        assertEquals(
            Category(
                id = "1",
                name = "Beef",
                description = "Beef is the culinary name for meat from cattle",
                thumb = "https://example.com/beef.png",
            ),
            result,
        )
    }

    @Test
    fun `map with all null fields returns empty strings`() {
        val dto =
            CategoryDTO(
                id = null,
                name = null,
                description = null,
                imageThumb = null,
            )

        val result = mapper.map(dto)

        assertEquals(
            Category(id = "", name = "", description = "", thumb = ""),
            result,
        )
    }

    @Test
    fun `map with partial null fields defaults only nulls to empty strings`() {
        val dto =
            CategoryDTO(
                id = "2",
                name = null,
                description = "Some description",
                imageThumb = null,
            )

        val result = mapper.map(dto)

        assertEquals("2", result.id)
        assertEquals("", result.name)
        assertEquals("Some description", result.description)
        assertEquals("", result.thumb)
    }

    @Test
    fun `map list of DTOs returns list of Categories`() {
        val dtos =
            listOf(
                CategoryDTO(id = "1", name = "Beef", description = "Desc1", imageThumb = "thumb1"),
                CategoryDTO(id = "2", name = "Chicken", description = "Desc2", imageThumb = "thumb2"),
            )

        val result = mapper.map(dtos)

        assertEquals(2, result.size)
        assertEquals("Beef", result[0].name)
        assertEquals("Chicken", result[1].name)
    }
}
