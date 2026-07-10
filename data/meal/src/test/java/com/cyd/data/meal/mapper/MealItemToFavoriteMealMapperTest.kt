package com.cyd.data.meal.mapper

import com.cyd.base.model.MealItem
import com.cyd.data.db.entity.FavoriteMealEntity
import org.junit.Assert.assertEquals
import org.junit.Test

class MealItemToFavoriteMealMapperTest {
    private val mapper = MealItemToFavoriteMealMapper()

    @Test
    fun `map returns correct FavoriteMealEntity from MealItem`() {
        val mealItem =
            MealItem(
                id = "52772",
                name = "Teriyaki Chicken",
                thumb = "https://example.com/thumb.png",
            )

        val result = mapper.map(mealItem)

        assertEquals(
            FavoriteMealEntity(id = "52772", name = "Teriyaki Chicken", thumb = "https://example.com/thumb.png"),
            result,
        )
    }

    @Test
    fun `map preserves empty strings`() {
        val mealItem = MealItem(id = "", name = "", thumb = "")

        val result = mapper.map(mealItem)

        assertEquals(FavoriteMealEntity(id = "", name = "", thumb = ""), result)
    }

    @Test
    fun `map list of MealItems returns list of FavoriteMealEntities`() {
        val mealItems =
            listOf(
                MealItem(id = "1", name = "Meal A", thumb = "tA"),
                MealItem(id = "2", name = "Meal B", thumb = "tB"),
            )

        val result = mapper.map(mealItems)

        assertEquals(2, result.size)
        assertEquals("1", result[0].id)
        assertEquals("2", result[1].id)
    }
}
