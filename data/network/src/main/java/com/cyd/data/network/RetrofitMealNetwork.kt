package com.cyd.data.network

import com.cyd.data.network.model.CategoriesResponse
import com.cyd.data.network.model.CategoryDTO
import com.cyd.data.network.model.MealDetailsDTO
import com.cyd.data.network.model.MealDetailsResponse
import com.cyd.data.network.model.MealListItemDTO
import com.cyd.data.network.model.MealListResponse
import com.cyd.data.network.model.RandomMealDTO
import com.cyd.data.network.model.RandomMealResponse
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query
import javax.inject.Inject
import javax.inject.Singleton

/**
 * https://www.themealdb.com/api.php
 */
private interface MealNetworkApi {

    @GET("random.php")
    suspend fun getRandomMeal(): RandomMealResponse

    @GET("categories.php")
    suspend fun getMealCategories(): CategoriesResponse

    /*www.themealdb.com/api/json/v1/1/filter.php?c=Seafood*/
    @GET("filter.php")
    suspend fun getMealsByCategory(
        @Query("c") name: String,
    ): MealListResponse

    /*www.themealdb.com/api/json/v1/1/lookup.php?i=52772*/
    @GET("lookup.php")
    suspend fun getMealDetails(
        @Query("i") idMeal: String,
    ): MealDetailsResponse
}

@Singleton
class RetrofitMealNetwork @Inject constructor() : MealDataSource {

    private val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .build()
        .create(MealNetworkApi::class.java)

    override suspend fun getRandomMeal(): RandomMealDTO? {
        return retrofit.getRandomMeal().meals.firstOrNull()
    }

    override suspend fun getMealCategories(): List<CategoryDTO>? {
        return retrofit.getMealCategories().categories
    }

    override suspend fun getMealsByCategory(name: String): List<MealListItemDTO>? {
        return retrofit.getMealsByCategory(name).meals
    }

    override suspend fun getMealDetails(idMeal: String): MealDetailsDTO? {
        return retrofit.getMealDetails(idMeal).meals?.firstOrNull()
    }
}