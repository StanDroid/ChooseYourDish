package com.cyd.data.meal.mapper

import com.cyd.base.model.RandomMeal
import com.cyd.data.network.model.RandomMealDTO
import org.junit.Assert.assertEquals
import org.junit.Test

class RandomMealMapperTest {
    private val mapper = RandomMealMapper()

    @Test
    fun `map with all fields populated returns correct RandomMeal`() {
        val dto =
            RandomMealDTO(
                dateModified = null,
                idMeal = "52772",
                strArea = "Japanese",
                strCategory = "Chicken",
                strCreativeCommonsConfirmed = null,
                strDrinkAlternate = null,
                strImageSource = null,
                strIngredient1 = null,
                strIngredient2 = null,
                strIngredient3 = null,
                strIngredient4 = null,
                strIngredient5 = null,
                strIngredient6 = null,
                strIngredient7 = null,
                strIngredient8 = null,
                strIngredient9 = null,
                strIngredient10 = null,
                strIngredient11 = null,
                strIngredient12 = null,
                strIngredient13 = null,
                strIngredient14 = null,
                strIngredient15 = null,
                strIngredient16 = null,
                strIngredient17 = null,
                strIngredient18 = null,
                strIngredient19 = null,
                strIngredient20 = null,
                strInstructions = "Cook it well",
                strMeal = "Teriyaki Chicken",
                strMealThumb = "https://example.com/thumb.png",
                strMeasure1 = null,
                strMeasure2 = null,
                strMeasure3 = null,
                strMeasure4 = null,
                strMeasure5 = null,
                strMeasure6 = null,
                strMeasure7 = null,
                strMeasure8 = null,
                strMeasure9 = null,
                strMeasure10 = null,
                strMeasure11 = null,
                strMeasure12 = null,
                strMeasure13 = null,
                strMeasure14 = null,
                strMeasure15 = null,
                strMeasure16 = null,
                strMeasure17 = null,
                strMeasure18 = null,
                strMeasure19 = null,
                strMeasure20 = null,
                strSource = "https://example.com/recipe",
                strTags = null,
                strYoutube = "https://youtube.com/watch?v=123",
            )

        val result = mapper.map(dto)

        assertEquals(
            RandomMeal(
                idMeal = "52772",
                strArea = "Japanese",
                strMeal = "Teriyaki Chicken",
                strCategory = "Chicken",
                strInstructions = "Cook it well",
                strMealThumb = "https://example.com/thumb.png",
                strSource = "https://example.com/recipe",
                strYoutube = "https://youtube.com/watch?v=123",
            ),
            result,
        )
    }

    @Test
    fun `map with all null fields returns empty strings`() {
        val dto =
            RandomMealDTO(
                dateModified = null,
                idMeal = null,
                strArea = null,
                strCategory = null,
                strCreativeCommonsConfirmed = null,
                strDrinkAlternate = null,
                strImageSource = null,
                strIngredient1 = null,
                strIngredient2 = null,
                strIngredient3 = null,
                strIngredient4 = null,
                strIngredient5 = null,
                strIngredient6 = null,
                strIngredient7 = null,
                strIngredient8 = null,
                strIngredient9 = null,
                strIngredient10 = null,
                strIngredient11 = null,
                strIngredient12 = null,
                strIngredient13 = null,
                strIngredient14 = null,
                strIngredient15 = null,
                strIngredient16 = null,
                strIngredient17 = null,
                strIngredient18 = null,
                strIngredient19 = null,
                strIngredient20 = null,
                strInstructions = null,
                strMeal = null,
                strMealThumb = null,
                strMeasure1 = null,
                strMeasure2 = null,
                strMeasure3 = null,
                strMeasure4 = null,
                strMeasure5 = null,
                strMeasure6 = null,
                strMeasure7 = null,
                strMeasure8 = null,
                strMeasure9 = null,
                strMeasure10 = null,
                strMeasure11 = null,
                strMeasure12 = null,
                strMeasure13 = null,
                strMeasure14 = null,
                strMeasure15 = null,
                strMeasure16 = null,
                strMeasure17 = null,
                strMeasure18 = null,
                strMeasure19 = null,
                strMeasure20 = null,
                strSource = null,
                strTags = null,
                strYoutube = null,
            )

        val result = mapper.map(dto)

        assertEquals(
            RandomMeal(
                idMeal = "",
                strArea = "",
                strMeal = "",
                strCategory = "",
                strInstructions = "",
                strMealThumb = "",
                strSource = "",
                strYoutube = "",
            ),
            result,
        )
    }
}
