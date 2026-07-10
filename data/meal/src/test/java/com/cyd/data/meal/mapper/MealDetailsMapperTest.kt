package com.cyd.data.meal.mapper

import com.cyd.data.network.model.MealDetailsDTO
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertNull
import org.junit.Test

class MealDetailsMapperTest {
    private val mapper = MealDetailsMapper()

    private fun createDto(
        idMeal: String? = "52772",
        strMeal: String? = "Teriyaki Chicken",
        strArea: String? = "Japanese",
        strCategory: String? = "Chicken",
        strInstructions: String? = "Cook well",
        strMealThumb: String? = "thumb.png",
        strSource: String? = "source.com",
        strYoutube: String? = "youtube.com",
        strTags: String? = "Meat,Chicken",
        dateModified: String? = null,
        strCreativeCommonsConfirmed: String? = null,
        strDrinkAlternate: String? = null,
        strImageSource: String? = null,
        ingredients: Map<Int, String?> = emptyMap(),
        measures: Map<Int, String?> = emptyMap(),
    ): MealDetailsDTO =
        MealDetailsDTO(
            idMeal = idMeal,
            strMeal = strMeal,
            strArea = strArea,
            strCategory = strCategory,
            strInstructions = strInstructions,
            strMealThumb = strMealThumb,
            strSource = strSource,
            strYoutube = strYoutube,
            strTags = strTags,
            dateModified = dateModified,
            strCreativeCommonsConfirmed = strCreativeCommonsConfirmed,
            strDrinkAlternate = strDrinkAlternate,
            strImageSource = strImageSource,
            strIngredient1 = ingredients[1],
            strIngredient2 = ingredients[2],
            strIngredient3 = ingredients[3],
            strIngredient4 = ingredients[4],
            strIngredient5 = ingredients[5],
            strIngredient6 = ingredients[6],
            strIngredient7 = ingredients[7],
            strIngredient8 = ingredients[8],
            strIngredient9 = ingredients[9],
            strIngredient10 = ingredients[10],
            strIngredient11 = ingredients[11],
            strIngredient12 = ingredients[12],
            strIngredient13 = ingredients[13],
            strIngredient14 = ingredients[14],
            strIngredient15 = ingredients[15],
            strIngredient16 = ingredients[16],
            strIngredient17 = ingredients[17],
            strIngredient18 = ingredients[18],
            strIngredient19 = ingredients[19],
            strIngredient20 = ingredients[20],
            strMeasure1 = measures[1],
            strMeasure2 = measures[2],
            strMeasure3 = measures[3],
            strMeasure4 = measures[4],
            strMeasure5 = measures[5],
            strMeasure6 = measures[6],
            strMeasure7 = measures[7],
            strMeasure8 = measures[8],
            strMeasure9 = measures[9],
            strMeasure10 = measures[10],
            strMeasure11 = measures[11],
            strMeasure12 = measures[12],
            strMeasure13 = measures[13],
            strMeasure14 = measures[14],
            strMeasure15 = measures[15],
            strMeasure16 = measures[16],
            strMeasure17 = measures[17],
            strMeasure18 = measures[18],
            strMeasure19 = measures[19],
            strMeasure20 = measures[20],
        )

    @Test
    fun `map with all fields populated returns correct Meal`() {
        val dto =
            createDto(
                ingredients = mapOf(1 to "Soy Sauce", 2 to "Chicken"),
                measures = mapOf(1 to "3/4 cup", 2 to "2 lbs"),
            )

        val result = mapper.map(dto)

        assertEquals("52772", result.id)
        assertEquals("Teriyaki Chicken", result.meal)
        assertEquals("Japanese", result.area)
        assertEquals("Chicken", result.category)
        assertEquals("Cook well", result.instructions)
        assertEquals("thumb.png", result.mealThumb)
        assertEquals("source.com", result.source)
        assertEquals("youtube.com", result.youtube)
        assertEquals("Meat,Chicken", result.tags)
    }

    @Test
    fun `map extracts ingredients with measures correctly`() {
        val dto =
            createDto(
                ingredients = mapOf(1 to "Soy Sauce", 2 to "Chicken", 3 to "Rice"),
                measures = mapOf(1 to "3/4 cup", 2 to "2 lbs", 3 to "1 cup"),
            )

        val result = mapper.map(dto)

        assertEquals(3, result.mealIngredients.size)
        assertEquals("Soy Sauce", result.mealIngredients[0].name)
        assertEquals("3/4 cup", result.mealIngredients[0].measure)
        assertEquals("Chicken", result.mealIngredients[1].name)
        assertEquals("2 lbs", result.mealIngredients[1].measure)
        assertEquals("Rice", result.mealIngredients[2].name)
        assertEquals("1 cup", result.mealIngredients[2].measure)
    }

    @Test
    fun `map skips empty ingredient names`() {
        val dto =
            createDto(
                ingredients = mapOf(1 to "Soy Sauce", 2 to "", 3 to "Rice"),
                measures = mapOf(1 to "3/4 cup", 2 to "1 tsp", 3 to "1 cup"),
            )

        val result = mapper.map(dto)

        assertEquals(2, result.mealIngredients.size)
        assertEquals("Soy Sauce", result.mealIngredients[0].name)
        assertEquals("Rice", result.mealIngredients[1].name)
    }

    @Test
    fun `map skips null ingredient names`() {
        val dto =
            createDto(
                ingredients = mapOf(1 to "Soy Sauce", 3 to "Rice"),
                measures = mapOf(1 to "3/4 cup", 2 to "1 tsp", 3 to "1 cup"),
            )

        val result = mapper.map(dto)

        assertEquals(2, result.mealIngredients.size)
        assertEquals("Soy Sauce", result.mealIngredients[0].name)
        assertEquals("Rice", result.mealIngredients[1].name)
    }

    @Test
    fun `map with no ingredients returns empty ingredient list`() {
        val dto = createDto()

        val result = mapper.map(dto)

        assertNotNull(result.mealIngredients)
        assertEquals(0, result.mealIngredients.size)
    }

    @Test
    fun `map with ingredient but null measure sets null measure`() {
        val dto =
            createDto(
                ingredients = mapOf(1 to "Soy Sauce"),
                measures = emptyMap(),
            )

        val result = mapper.map(dto)

        assertEquals(1, result.mealIngredients.size)
        assertEquals("Soy Sauce", result.mealIngredients[0].name)
        assertNull(result.mealIngredients[0].measure)
    }

    @Test
    fun `map with null base fields passes them through`() {
        val dto =
            createDto(
                idMeal = null,
                strMeal = null,
                strArea = null,
                strCategory = null,
                strInstructions = null,
                strMealThumb = null,
                strSource = null,
                strYoutube = null,
                strTags = null,
            )

        val result = mapper.map(dto)

        assertNull(result.id)
        assertNull(result.meal)
        assertNull(result.area)
        assertNull(result.category)
        assertNull(result.instructions)
        assertNull(result.mealThumb)
        assertNull(result.source)
        assertNull(result.youtube)
        assertNull(result.tags)
    }
}
