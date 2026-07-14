package com.cyd.data.ingredients.mapper

import com.cyd.base.model.Ingredient
import com.cyd.data.network.model.IngredientDTO
import org.junit.Assert.assertEquals
import org.junit.Test

class IngredientsMapperTest {
    private val mapper = IngredientsMapper()

    @Test
    fun `map with all fields populated returns correct Ingredient`() {
        val dto =
            IngredientDTO(
                id = "1",
                name = "Chicken",
                description = "A versatile protein",
            )

        val result = mapper.map(dto)

        assertEquals(
            Ingredient(id = "1", name = "Chicken", description = "A versatile protein"),
            result,
        )
    }

    @Test
    fun `map with all null fields returns empty strings`() {
        val dto = IngredientDTO(id = null, name = null, description = null)

        val result = mapper.map(dto)

        assertEquals(Ingredient(id = "", name = "", description = ""), result)
    }

    @Test
    fun `map with partial null fields defaults only nulls to empty strings`() {
        val dto = IngredientDTO(id = "5", name = null, description = "Spicy")

        val result = mapper.map(dto)

        assertEquals("5", result.id)
        assertEquals("", result.name)
        assertEquals("Spicy", result.description)
    }

    @Test
    fun `map list of DTOs returns list of Ingredients`() {
        val dtos =
            listOf(
                IngredientDTO(id = "1", name = "Chicken", description = "Desc1"),
                IngredientDTO(id = "2", name = "Rice", description = "Desc2"),
            )

        val result = mapper.map(dtos)

        assertEquals(2, result.size)
        assertEquals("Chicken", result[0].name)
        assertEquals("Rice", result[1].name)
    }
}
