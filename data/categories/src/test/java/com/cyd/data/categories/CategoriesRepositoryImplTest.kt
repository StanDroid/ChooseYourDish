package com.cyd.data.categories

import com.cyd.base.CydDispatchers
import com.cyd.base.model.Category
import com.cyd.data.categories.mapper.CategoriesMapper
import com.cyd.data.network.MealDataSource
import com.cyd.data.network.model.CategoryDTO
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
class CategoriesRepositoryImplTest {
    private lateinit var mealDataSource: MealDataSource
    private lateinit var categoriesMapper: CategoriesMapper
    private lateinit var repository: CategoriesRepositoryImpl

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
        categoriesMapper = mockk()
        repository = CategoriesRepositoryImpl(mealDataSource, categoriesMapper, testDispatchers)
    }

    @Test
    fun `getMealCategories returns mapped categories when data source returns data`() =
        runTest {
            val dto = CategoryDTO(id = "1", name = "Beef", description = "Desc", imageThumb = "thumb")
            val category = Category(id = "1", name = "Beef", description = "Desc", thumb = "thumb")

            coEvery { mealDataSource.getMealCategories() } returns listOf(dto)
            every { categoriesMapper.map(dto) } returns category

            val result = repository.getMealCategories()

            assertEquals(listOf(category), result)
        }

    @Test
    fun `getMealCategories returns empty list when data source returns null`() =
        runTest {
            coEvery { mealDataSource.getMealCategories() } returns null

            val result = repository.getMealCategories()

            assertTrue(result.isEmpty())
        }

    @Test
    fun `getMealCategories returns empty list when data source returns empty list`() =
        runTest {
            coEvery { mealDataSource.getMealCategories() } returns emptyList()

            val result = repository.getMealCategories()

            assertTrue(result.isEmpty())
        }

    @Test
    fun `getMealCategories maps multiple categories correctly`() =
        runTest {
            val dto1 = CategoryDTO(id = "1", name = "Beef", description = "D1", imageThumb = "t1")
            val dto2 = CategoryDTO(id = "2", name = "Chicken", description = "D2", imageThumb = "t2")
            val cat1 = Category(id = "1", name = "Beef", description = "D1", thumb = "t1")
            val cat2 = Category(id = "2", name = "Chicken", description = "D2", thumb = "t2")

            coEvery { mealDataSource.getMealCategories() } returns listOf(dto1, dto2)
            every { categoriesMapper.map(dto1) } returns cat1
            every { categoriesMapper.map(dto2) } returns cat2

            val result = repository.getMealCategories()

            assertEquals(2, result.size)
            assertEquals(cat1, result[0])
            assertEquals(cat2, result[1])
        }
}
