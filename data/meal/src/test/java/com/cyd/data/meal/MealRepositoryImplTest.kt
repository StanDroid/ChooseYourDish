package com.cyd.data.meal

import app.cash.turbine.test
import com.cyd.base.CydDispatchers
import com.cyd.base.model.Meal
import com.cyd.base.model.MealItem
import com.cyd.base.model.RandomMeal
import com.cyd.data.db.FavoriteMealDao
import com.cyd.data.db.entity.FavoriteMealEntity
import com.cyd.data.meal.mapper.FavoriteMealToMealItemMapper
import com.cyd.data.meal.mapper.MealDetailsMapper
import com.cyd.data.meal.mapper.MealItemToFavoriteMealMapper
import com.cyd.data.meal.mapper.MealListItemMapper
import com.cyd.data.meal.mapper.RandomMealMapper
import com.cyd.data.network.MealDataSource
import com.cyd.data.network.model.MealDetailsDTO
import com.cyd.data.network.model.MealListItemDTO
import com.cyd.data.network.model.RandomMealDTO
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNull
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class MealRepositoryImplTest {
    private lateinit var mealDataSource: MealDataSource
    private lateinit var randomMealMapper: RandomMealMapper
    private lateinit var mealListItemMapper: MealListItemMapper
    private lateinit var mealDetailsMapper: MealDetailsMapper
    private lateinit var favoriteMealDao: FavoriteMealDao
    private lateinit var favoriteMealToMealItemMapper: FavoriteMealToMealItemMapper
    private lateinit var mealItemToFavoriteMealMapper: MealItemToFavoriteMealMapper
    private lateinit var repository: MealRepositoryImpl

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
        randomMealMapper = mockk()
        mealListItemMapper = mockk()
        mealDetailsMapper = mockk()
        favoriteMealDao = mockk()
        favoriteMealToMealItemMapper = mockk()
        mealItemToFavoriteMealMapper = mockk()

        repository =
            MealRepositoryImpl(
                mealDataSource,
                randomMealMapper,
                mealListItemMapper,
                mealDetailsMapper,
                favoriteMealDao,
                favoriteMealToMealItemMapper,
                mealItemToFavoriteMealMapper,
                testDispatchers,
            )
    }

    // region getRandomMeal
    @Test
    fun `getRandomMeal returns mapped meal when data source returns data`() =
        runTest {
            val dto = mockk<RandomMealDTO>()
            val randomMeal =
                RandomMeal(
                    idMeal = "1",
                    strMeal = "Meal",
                    strArea = "Area",
                    strCategory = "Cat",
                    strInstructions = "Instr",
                    strMealThumb = "thumb",
                    strYoutube = "yt",
                    strSource = "src",
                )

            coEvery { mealDataSource.getRandomMeal() } returns dto
            every { randomMealMapper.map(dto) } returns randomMeal

            val result = repository.getRandomMeal()

            assertEquals(randomMeal, result)
        }

    @Test
    fun `getRandomMeal returns null when data source returns null`() =
        runTest {
            coEvery { mealDataSource.getRandomMeal() } returns null

            val result = repository.getRandomMeal()

            assertNull(result)
        }
    // endregion

    // region getMealsByCategory
    @Test
    fun `getMealsByCategory returns mapped list`() =
        runTest {
            val dto = MealListItemDTO(idMeal = "1", strMeal = "Meal", strMealThumb = "thumb")
            val mealItem = MealItem(id = "1", name = "Meal", thumb = "thumb")

            coEvery { mealDataSource.getMealsByCategory("Beef") } returns listOf(dto)
            every { mealListItemMapper.map(dto) } returns mealItem

            val result = repository.getMealsByCategory("Beef")

            assertEquals(listOf(mealItem), result)
        }

    @Test
    fun `getMealsByCategory returns empty list when data source returns null`() =
        runTest {
            coEvery { mealDataSource.getMealsByCategory("Beef") } returns null

            val result = repository.getMealsByCategory("Beef")

            assertTrue(result.isEmpty())
        }
    // endregion

    // region getMealDetails
    @Test
    fun `getMealDetails returns mapped meal`() =
        runTest {
            val dto = mockk<MealDetailsDTO>()
            val meal = Meal(id = "1", meal = "Test Meal")

            coEvery { mealDataSource.getMealDetails("1") } returns dto
            every { mealDetailsMapper.map(dto) } returns meal

            val result = repository.getMealDetails("1")

            assertEquals(meal, result)
        }

    @Test
    fun `getMealDetails returns null when data source returns null`() =
        runTest {
            coEvery { mealDataSource.getMealDetails("1") } returns null

            val result = repository.getMealDetails("1")

            assertNull(result)
        }
    // endregion

    // region getMealsByMainIngredient
    @Test
    fun `getMealsByMainIngredient returns mapped list`() =
        runTest {
            val dto = MealListItemDTO(idMeal = "1", strMeal = "Meal", strMealThumb = "thumb")
            val mealItem = MealItem(id = "1", name = "Meal", thumb = "thumb")

            coEvery { mealDataSource.getMealsByMainIngredient("Chicken") } returns listOf(dto)
            every { mealListItemMapper.map(dto) } returns mealItem

            val result = repository.getMealsByMainIngredient("Chicken")

            assertEquals(listOf(mealItem), result)
        }

    @Test
    fun `getMealsByMainIngredient returns empty list when null`() =
        runTest {
            coEvery { mealDataSource.getMealsByMainIngredient("Chicken") } returns null

            val result = repository.getMealsByMainIngredient("Chicken")

            assertTrue(result.isEmpty())
        }
    // endregion

    // region getFavoritesMeals
    @Test
    fun `getFavoritesMeals emits mapped favorite meals`() =
        runTest {
            val entity = FavoriteMealEntity(id = "1", name = "Fav Meal", thumb = "thumb")
            val mealItem = MealItem(id = "1", name = "Fav Meal", thumb = "thumb")

            every { favoriteMealDao.getFavoriteMeals() } returns flowOf(listOf(entity))
            every { favoriteMealToMealItemMapper.map(listOf(entity)) } returns listOf(mealItem)

            repository.getFavoritesMeals().test {
                assertEquals(listOf(mealItem), awaitItem())
                awaitComplete()
            }
        }

    @Test
    fun `getFavoritesMeals emits empty list when no favorites`() =
        runTest {
            every { favoriteMealDao.getFavoriteMeals() } returns flowOf(emptyList())
            every { favoriteMealToMealItemMapper.map(emptyList<FavoriteMealEntity>()) } returns emptyList()

            repository.getFavoritesMeals().test {
                assertEquals(emptyList<MealItem>(), awaitItem())
                awaitComplete()
            }
        }
    // endregion

    // region getFavoritesMealIds
    @Test
    fun `getFavoritesMealIds returns IDs`() =
        runTest {
            coEvery { favoriteMealDao.getFavoriteMealIds() } returns listOf("1", "2", "3")

            val result = repository.getFavoritesMealIds()

            assertEquals(listOf("1", "2", "3"), result)
        }

    @Test
    fun `getFavoritesMealIds returns empty list when null`() =
        runTest {
            coEvery { favoriteMealDao.getFavoriteMealIds() } returns null

            val result = repository.getFavoritesMealIds()

            assertTrue(result.isEmpty())
        }
    // endregion

    // region insertFavoriteMeal
    @Test
    fun `insertFavoriteMeal maps and inserts via DAO`() =
        runTest {
            val mealItem = MealItem(id = "1", name = "Meal", thumb = "thumb")
            val entity = FavoriteMealEntity(id = "1", name = "Meal", thumb = "thumb")

            every { mealItemToFavoriteMealMapper.map(mealItem) } returns entity
            coEvery { favoriteMealDao.insertFavoriteMeal(entity) } returns Unit

            repository.insertFavoriteMeal(mealItem)

            coVerify { favoriteMealDao.insertFavoriteMeal(entity) }
        }
    // endregion

    // region removeFavoriteMeal
    @Test
    fun `removeFavoriteMeal maps and removes via DAO`() =
        runTest {
            val mealItem = MealItem(id = "1", name = "Meal", thumb = "thumb")
            val entity = FavoriteMealEntity(id = "1", name = "Meal", thumb = "thumb")

            every { mealItemToFavoriteMealMapper.map(mealItem) } returns entity
            coEvery { favoriteMealDao.removeFavoriteMeal(entity) } returns Unit

            repository.removeFavoriteMeal(mealItem)

            coVerify { favoriteMealDao.removeFavoriteMeal(entity) }
        }
    // endregion
}
