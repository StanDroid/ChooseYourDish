package com.cyd.data.network

import io.ktor.client.HttpClient
import io.ktor.client.engine.mock.MockEngine
import io.ktor.client.engine.mock.respond
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.http.ContentType
import io.ktor.http.HttpHeaders
import io.ktor.http.HttpStatusCode
import io.ktor.http.headersOf
import io.ktor.serialization.gson.gson
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertNull
import org.junit.Test

class KtorMealDataSourceImplTest {
    private fun createDataSource(
        responseBody: String,
        statusCode: HttpStatusCode = HttpStatusCode.OK,
    ): KtorMealDataSourceImpl {
        val mockEngine =
            MockEngine { _ ->
                respond(
                    content = responseBody,
                    status = statusCode,
                    headers = headersOf(HttpHeaders.ContentType, ContentType.Application.Json.toString()),
                )
            }
        val httpClient =
            HttpClient(mockEngine) {
                install(ContentNegotiation) { gson() }
                defaultRequest { url(BASE_URL) }
            }
        val ktorMealNetwork = mockk<KtorMealNetwork>()
        every { ktorMealNetwork.httpClient } returns httpClient
        return KtorMealDataSourceImpl(ktorMealNetwork)
    }

    // region getRandomMeal
    @Test
    fun `getRandomMeal returns meal on success`() =
        runTest {
            val json =
                """
                {
                    "meals": [{
                        "idMeal": "52772",
                        "strMeal": "Teriyaki Chicken",
                        "strArea": "Japanese",
                        "strCategory": "Chicken",
                        "strInstructions": "Cook it",
                        "strMealThumb": "thumb.png",
                        "strSource": "source.com",
                        "strYoutube": "yt.com"
                    }]
                }
                """.trimIndent()

            val dataSource = createDataSource(json)
            val result = dataSource.getRandomMeal()

            assertNotNull(result)
            assertEquals("52772", result?.idMeal)
            assertEquals("Teriyaki Chicken", result?.strMeal)
        }

    @Test
    fun `getRandomMeal returns null on empty meals list`() =
        runTest {
            val json = """{"meals": []}"""

            val dataSource = createDataSource(json)
            val result = dataSource.getRandomMeal()

            assertNull(result)
        }

    @Test
    fun `getRandomMeal returns null on network error`() =
        runTest {
            val mockEngine =
                MockEngine { _ ->
                    respond(
                        content = "Server Error",
                        status = HttpStatusCode.InternalServerError,
                        headers = headersOf(HttpHeaders.ContentType, ContentType.Text.Plain.toString()),
                    )
                }
            val httpClient =
                HttpClient(mockEngine) {
                    install(ContentNegotiation) { gson() }
                    defaultRequest { url(BASE_URL) }
                }
            val ktorMealNetwork = mockk<KtorMealNetwork>()
            every { ktorMealNetwork.httpClient } returns httpClient
            val dataSource = KtorMealDataSourceImpl(ktorMealNetwork)

            val result = dataSource.getRandomMeal()

            assertNull(result)
        }
    // endregion

    // region getMealCategories
    @Test
    fun `getMealCategories returns categories on success`() =
        runTest {
            val json =
                """
                {
                    "categories": [
                        {"idCategory": "1", "strCategory": "Beef", "strCategoryDescription": "Desc", "strCategoryThumb": "thumb"}
                    ]
                }
                """.trimIndent()

            val dataSource = createDataSource(json)
            val result = dataSource.getMealCategories()

            assertNotNull(result)
            assertEquals(1, result?.size)
            assertEquals("1", result?.first()?.id)
            assertEquals("Beef", result?.first()?.name)
        }

    @Test
    fun `getMealCategories returns null on error`() =
        runTest {
            val mockEngine =
                MockEngine { _ ->
                    respond(
                        content = "Error",
                        status = HttpStatusCode.InternalServerError,
                        headers = headersOf(HttpHeaders.ContentType, ContentType.Text.Plain.toString()),
                    )
                }
            val httpClient =
                HttpClient(mockEngine) {
                    install(ContentNegotiation) { gson() }
                    defaultRequest { url(BASE_URL) }
                }
            val ktorMealNetwork = mockk<KtorMealNetwork>()
            every { ktorMealNetwork.httpClient } returns httpClient
            val dataSource = KtorMealDataSourceImpl(ktorMealNetwork)

            val result = dataSource.getMealCategories()

            assertNull(result)
        }
    // endregion

    // region getMealsByCategory
    @Test
    fun `getMealsByCategory returns meals on success`() =
        runTest {
            val json =
                """
                {
                    "meals": [
                        {"idMeal": "1", "strMeal": "Meal 1", "strMealThumb": "t1"},
                        {"idMeal": "2", "strMeal": "Meal 2", "strMealThumb": "t2"}
                    ]
                }
                """.trimIndent()

            val dataSource = createDataSource(json)
            val result = dataSource.getMealsByCategory("Beef")

            assertNotNull(result)
            assertEquals(2, result?.size)
            assertEquals("Meal 1", result?.get(0)?.strMeal)
        }
    // endregion

    // region getMealsByMainIngredient
    @Test
    fun `getMealsByMainIngredient returns meals on success`() =
        runTest {
            val json =
                """
                {
                    "meals": [
                        {"idMeal": "1", "strMeal": "Meal 1", "strMealThumb": "t1"}
                    ]
                }
                """.trimIndent()

            val dataSource = createDataSource(json)
            val result = dataSource.getMealsByMainIngredient("Chicken")

            assertNotNull(result)
            assertEquals(1, result?.size)
            assertEquals("1", result?.first()?.idMeal)
        }
    // endregion

    // region getMealDetails
    @Test
    fun `getMealDetails returns meal details on success`() =
        runTest {
            val json =
                """
                {
                    "meals": [{
                        "idMeal": "52772",
                        "strMeal": "Teriyaki Chicken",
                        "strArea": "Japanese",
                        "strCategory": "Chicken",
                        "strInstructions": "Cook well",
                        "strMealThumb": "thumb.png",
                        "strIngredient1": "Soy Sauce",
                        "strMeasure1": "3/4 cup"
                    }]
                }
                """.trimIndent()

            val dataSource = createDataSource(json)
            val result = dataSource.getMealDetails("52772")

            assertNotNull(result)
            assertEquals("52772", result?.idMeal)
            assertEquals("Teriyaki Chicken", result?.strMeal)
            assertEquals("Soy Sauce", result?.strIngredient1)
        }

    @Test
    fun `getMealDetails returns null when meals list is null`() =
        runTest {
            val json = """{"meals": null}"""

            val dataSource = createDataSource(json)
            val result = dataSource.getMealDetails("52772")

            assertNull(result)
        }
    // endregion

    // region getIngredients
    @Test
    fun `getIngredients returns ingredient list on success`() =
        runTest {
            val json =
                """
                {
                    "meals": [
                        {"idIngredient": "1", "strIngredient": "Chicken", "strDescription": "Poultry"},
                        {"idIngredient": "2", "strIngredient": "Salmon", "strDescription": "Fish"}
                    ]
                }
                """.trimIndent()

            val dataSource = createDataSource(json)
            val result = dataSource.getIngredients()

            assertNotNull(result)
            assertEquals(2, result?.size)
            assertEquals("Chicken", result?.get(0)?.name)
            assertEquals("Salmon", result?.get(1)?.name)
        }
    // endregion
}
