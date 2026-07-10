package com.cyd.data.db.converter

import com.cyd.base.model.MealIngredient
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test

class MealIngredientListConverterTest {
    private val converter = MealIngredientListConverter()

    @Test
    fun `round trip conversion preserves data`() {
        val ingredients =
            listOf(
                MealIngredient(name = "Chicken", measure = "2 lbs"),
                MealIngredient(name = "Soy Sauce", measure = "3/4 cup"),
                MealIngredient(name = "Rice", measure = "1 cup"),
            )

        val json = converter.fromMealIngredientList(ingredients)
        val result = converter.toMealIngredientList(json)

        assertEquals(3, result.size)
        assertEquals("Chicken", result[0].name)
        assertEquals("2 lbs", result[0].measure)
        assertEquals("Soy Sauce", result[1].name)
        assertEquals("3/4 cup", result[1].measure)
        assertEquals("Rice", result[2].name)
        assertEquals("1 cup", result[2].measure)
    }

    @Test
    fun `empty list converts to json and back`() {
        val ingredients = emptyList<MealIngredient>()

        val json = converter.fromMealIngredientList(ingredients)
        val result = converter.toMealIngredientList(json)

        assertTrue(result.isEmpty())
    }

    @Test
    fun `single item list converts correctly`() {
        val ingredients = listOf(MealIngredient(name = "Salt", measure = "1 tsp"))

        val json = converter.fromMealIngredientList(ingredients)
        val result = converter.toMealIngredientList(json)

        assertEquals(1, result.size)
        assertEquals("Salt", result[0].name)
        assertEquals("1 tsp", result[0].measure)
    }

    @Test
    fun `ingredient with null measure converts correctly`() {
        val ingredients = listOf(MealIngredient(name = "Pepper", measure = null))

        val json = converter.fromMealIngredientList(ingredients)
        val result = converter.toMealIngredientList(json)

        assertEquals(1, result.size)
        assertEquals("Pepper", result[0].name)
        assertEquals(null, result[0].measure)
    }

    @Test
    fun `ingredient with special characters converts correctly`() {
        val ingredients =
            listOf(
                MealIngredient(name = "Jalapeño (fresh)", measure = "2 \"large\" pieces"),
                MealIngredient(name = "Crème fraîche", measure = "1/2 cup"),
            )

        val json = converter.fromMealIngredientList(ingredients)
        val result = converter.toMealIngredientList(json)

        assertEquals(2, result.size)
        assertEquals("Jalapeño (fresh)", result[0].name)
        assertEquals("2 \"large\" pieces", result[0].measure)
        assertEquals("Crème fraîche", result[1].name)
        assertEquals("1/2 cup", result[1].measure)
    }

    @Test
    fun `fromMealIngredientList produces valid JSON`() {
        val ingredients = listOf(MealIngredient(name = "Chicken", measure = "1 lb"))

        val json = converter.fromMealIngredientList(ingredients)

        assertTrue(json.startsWith("["))
        assertTrue(json.endsWith("]"))
        assertTrue(json.contains("Chicken"))
    }
}
