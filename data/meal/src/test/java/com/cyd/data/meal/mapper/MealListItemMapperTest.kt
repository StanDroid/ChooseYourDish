package com.cyd.data.meal.mapper

import com.cyd.base.model.MealItem
import com.cyd.data.network.model.MealListItemDTO
import org.junit.Assert.assertEquals
import org.junit.Test

class MealListItemMapperTest {
    private val mapper = MealListItemMapper()

    @Test
    fun `map with all fields populated returns correct MealItem`() {
        val dto =
            MealListItemDTO(
                idMeal = "52772",
                strMeal = "Teriyaki Chicken",
                strMealThumb = "https://example.com/thumb.png",
            )

        val result = mapper.map(dto)

        assertEquals(
            MealItem(id = "52772", name = "Teriyaki Chicken", thumb = "https://example.com/thumb.png"),
            result,
        )
    }

    @Test
    fun `map with all null fields returns empty strings`() {
        val dto = MealListItemDTO(idMeal = null, strMeal = null, strMealThumb = null)

        val result = mapper.map(dto)

        assertEquals(MealItem(id = "", name = "", thumb = ""), result)
    }

    @Test
    fun `map with partial null fields defaults only nulls to empty`() {
        val dto = MealListItemDTO(idMeal = "123", strMeal = null, strMealThumb = "thumb.jpg")

        val result = mapper.map(dto)

        assertEquals("123", result.id)
        assertEquals("", result.name)
        assertEquals("thumb.jpg", result.thumb)
    }

    @Test
    fun `map list of DTOs returns list of MealItems`() {
        val dtos =
            listOf(
                MealListItemDTO(idMeal = "1", strMeal = "Meal 1", strMealThumb = "t1"),
                MealListItemDTO(idMeal = "2", strMeal = "Meal 2", strMealThumb = "t2"),
                MealListItemDTO(idMeal = "3", strMeal = "Meal 3", strMealThumb = "t3"),
            )

        val result = mapper.map(dtos)

        assertEquals(3, result.size)
        assertEquals("Meal 1", result[0].name)
        assertEquals("Meal 2", result[1].name)
        assertEquals("Meal 3", result[2].name)
    }
}
