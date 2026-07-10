package com.cyd.data.ingredients

import com.cyd.base.CydDispatchers
import com.cyd.base.model.Ingredient
import com.cyd.data.ingredients.mapper.IngredientsMapper
import com.cyd.data.network.MealDataSource
import com.cyd.data.network.model.IngredientDTO
import io.mockk.coEvery
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class IngredientsRepositoryImplTest {
    private lateinit var mealDataSource: MealDataSource
    private lateinit var ingredientsMapper: IngredientsMapper
    private lateinit var repository: IngredientsRepositoryImpl

    private val testDispatcher = UnconfinedTestDispatcher()
    private val testDispatchers =
        object : CydDispatchers {
            override val default = testDispatcher
            override val io = testDispatcher
            override val main = testDispatcher
            override val mainImmediate = testDispatcher
            override val unconfined = testDispatcher
        }

    @Before
    fun setUp() {
        mealDataSource = mockk()
        ingredientsMapper = mockk()
        repository = IngredientsRepositoryImpl(mealDataSource, ingredientsMapper, testDispatchers)
    }

    @Test
    fun `getIngredients returns mapped ingredients when data source returns data`() =
        runTest {
            val dto = IngredientDTO(id = "1", name = "Chicken", description = "Desc")
            val ingredient = Ingredient(id = "1", name = "Chicken", description = "Desc")

            coEvery { mealDataSource.getIngredients() } returns listOf(dto)
            every { ingredientsMapper.map(dto) } returns ingredient

            val result = repository.getIngredients()

            assertEquals(listOf(ingredient), result)
        }

    @Test
    fun `getIngredients returns empty list when data source returns null`() =
        runTest {
            coEvery { mealDataSource.getIngredients() } returns null

            val result = repository.getIngredients()

            assertTrue(result.isEmpty())
        }

    @Test
    fun `getIngredients returns empty list when data source returns empty list`() =
        runTest {
            coEvery { mealDataSource.getIngredients() } returns emptyList()

            val result = repository.getIngredients()

            assertTrue(result.isEmpty())
        }

    @Test
    fun `getIngredients maps multiple ingredients correctly`() =
        runTest {
            val dto1 = IngredientDTO(id = "1", name = "Chicken", description = "D1")
            val dto2 = IngredientDTO(id = "2", name = "Rice", description = "D2")
            val ing1 = Ingredient(id = "1", name = "Chicken", description = "D1")
            val ing2 = Ingredient(id = "2", name = "Rice", description = "D2")

            coEvery { mealDataSource.getIngredients() } returns listOf(dto1, dto2)
            every { ingredientsMapper.map(dto1) } returns ing1
            every { ingredientsMapper.map(dto2) } returns ing2

            val result = repository.getIngredients()

            assertEquals(2, result.size)
            assertEquals(ing1, result[0])
            assertEquals(ing2, result[1])
        }
}
