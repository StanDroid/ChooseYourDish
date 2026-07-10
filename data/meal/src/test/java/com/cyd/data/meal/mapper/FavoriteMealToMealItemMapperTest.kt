package com.cyd.data.meal.mapper

import com.cyd.base.model.MealItem
import com.cyd.data.db.entity.FavoriteMealEntity
import org.junit.Assert.assertEquals
import org.junit.Test

class FavoriteMealToMealItemMapperTest {
    private val mapper = FavoriteMealToMealItemMapper()

    @Test
    fun `map returns correct MealItem from FavoriteMealEntity`() {
        val entity =
            FavoriteMealEntity(
                id = "52772",
                name = "Teriyaki Chicken",
                thumb = "https://example.com/thumb.png",
            )

        val result = mapper.map(entity)

        assertEquals(
            MealItem(id = "52772", name = "Teriyaki Chicken", thumb = "https://example.com/thumb.png"),
            result,
        )
    }

    @Test
    fun `map preserves empty strings`() {
        val entity = FavoriteMealEntity(id = "", name = "", thumb = "")

        val result = mapper.map(entity)

        assertEquals(MealItem(id = "", name = "", thumb = ""), result)
    }

    @Test
    fun `map list of entities returns list of MealItems`() {
        val entities =
            listOf(
                FavoriteMealEntity(id = "1", name = "Meal A", thumb = "tA"),
                FavoriteMealEntity(id = "2", name = "Meal B", thumb = "tB"),
            )

        val result = mapper.map(entities)

        assertEquals(2, result.size)
        assertEquals("Meal A", result[0].name)
        assertEquals("Meal B", result[1].name)
    }
}
