package com.cyd.data.network

import com.cyd.data.network.model.CategoriesResponse
import com.cyd.data.network.model.CategoryDTO
import com.cyd.data.network.model.IngredientDTO
import com.cyd.data.network.model.IngredientsResponse
import com.cyd.data.network.model.MealDetailsDTO
import com.cyd.data.network.model.MealDetailsResponse
import com.cyd.data.network.model.MealListItemDTO
import com.cyd.data.network.model.MealListResponse
import com.cyd.data.network.model.RandomMealDTO
import com.cyd.data.network.model.RandomMealResponse
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class KtorMealDataSourceImpl @Inject constructor(
    private val ktor: KtorMealNetwork
) : MealDataSource {

    override suspend fun getRandomMeal(): RandomMealDTO? {
        return ktor.httpClient.getResult<RandomMealResponse>("random.php")
            .map { it.meals.firstOrNull() }
            .getOrNull()
    }

    override suspend fun getMealCategories(): List<CategoryDTO>? {
        return ktor.httpClient.getResult<CategoriesResponse>("categories.php")
            .map { it.categories }
            .getOrNull()
    }

    override suspend fun getMealsByCategory(name: String): List<MealListItemDTO>? {
        return ktor.httpClient.get("filter.php") {
            parameter("c", name)
        }.body<MealListResponse>().meals
    }

    override suspend fun getMealsByMainIngredient(name: String): List<MealListItemDTO>? {
        return ktor.httpClient.get("filter.php") {
            parameter("i", name)
        }.body<MealListResponse>().meals
    }

    override suspend fun getMealDetails(idMeal: String): MealDetailsDTO? {
        return ktor.httpClient.get("lookup.php") {
            parameter("i", idMeal)
        }.body<MealDetailsResponse>().meals?.firstOrNull()
    }

    override suspend fun getIngredients(): List<IngredientDTO>? {
        return ktor.httpClient.get("list.php") {
            parameter("i", "list")
        }.body<IngredientsResponse>().ingredients
    }

    suspend inline fun <reified R> HttpClient.getResult(
        urlString: String,
        builder: HttpRequestBuilder.() -> Unit = {}
    ): Result<R> = runCatching { get(urlString, builder).body() }
}
