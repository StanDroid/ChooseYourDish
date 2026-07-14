package com.cyd.feature.categorymeals.viewmodel

import app.cash.turbine.test
import com.cyd.base.model.MealItem
import com.cyd.base.utils.ErrorHandler
import com.cyd.base.viewmodel.UiState
import com.cyd.domain.meal.categorymeals.GetFavoriteMealListUseCase
import com.cyd.domain.meal.categorymeals.GetMealListByIngredientUseCase
import com.cyd.domain.meal.categorymeals.GetMealListUseCase
import com.cyd.testing.MainDispatcherRule
import com.cyd.testing.TestCydDispatchers
import io.mockk.Runs
import io.mockk.coEvery
import io.mockk.every
import io.mockk.just
import io.mockk.mockk
import io.mockk.mockkObject
import io.mockk.unmockkAll
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class MealListViewModelTest {
    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    private val useCase: GetMealListUseCase = mockk()
    private val useCaseByIngredientUseCase: GetMealListByIngredientUseCase = mockk()
    private val getFavoriteMealListUseCase: GetFavoriteMealListUseCase = mockk()
    private val cydDispatchers = TestCydDispatchers(mainDispatcherRule.testDispatcher)

    @Before
    fun setUp() {
        mockkObject(ErrorHandler)
        every { ErrorHandler.printStackTrace(any()) } just Runs
    }

    @After
    fun tearDown() {
        unmockkAll()
    }

    @Test
    fun `when loadMeals with Category then uiState reflects success`() =
        runTest {
            val mockMeals = listOf(MealItem("1", "Beef Wellington", "url"))
            coEvery { useCase.execute("Beef") } returns mockMeals
            val viewModel = MealListViewModel(useCase, useCaseByIngredientUseCase, getFavoriteMealListUseCase, cydDispatchers)

            viewModel.uiState.test {
                viewModel.loadMeals(MealType.Category("Beef"))
                mainDispatcherRule.testDispatcher.scheduler.advanceUntilIdle()

                val state = expectMostRecentItem()
                assertTrue(state is UiState.HasData)
                assertEquals(mockMeals, (state as UiState.HasData).data)
            }
        }

    @Test
    fun `when loadMeals with Ingredient then uiState reflects success`() =
        runTest {
            val mockMeals = listOf(MealItem("2", "Chicken Salad", "url"))
            coEvery { useCaseByIngredientUseCase.execute("Chicken") } returns mockMeals
            val viewModel = MealListViewModel(useCase, useCaseByIngredientUseCase, getFavoriteMealListUseCase, cydDispatchers)

            viewModel.uiState.test {
                viewModel.loadMeals(MealType.Ingredient("Chicken"))
                mainDispatcherRule.testDispatcher.scheduler.advanceUntilIdle()

                val state = expectMostRecentItem()
                assertTrue(state is UiState.HasData)
                assertEquals(mockMeals, (state as UiState.HasData).data)
            }
        }

    @Test
    fun `when loadMeals with Favorites then uiState reflects success`() =
        runTest {
            val mockMeals = listOf(MealItem("3", "Pancakes", "url"))
            coEvery { getFavoriteMealListUseCase.execute(null) } returns flowOf(mockMeals)
            val viewModel = MealListViewModel(useCase, useCaseByIngredientUseCase, getFavoriteMealListUseCase, cydDispatchers)

            viewModel.uiState.test {
                viewModel.loadMeals(MealType.Favorites)
                mainDispatcherRule.testDispatcher.scheduler.advanceUntilIdle()

                val state = expectMostRecentItem()
                assertTrue(state is UiState.HasData)
                assertEquals(mockMeals, (state as UiState.HasData).data)
            }
        }

    @Test
    fun `when useCase throws exception then uiState reflects error`() =
        runTest {
            coEvery { useCase.execute("Beef") } throws RuntimeException("Network error")
            val viewModel =
                MealListViewModel(
                    useCase,
                    useCaseByIngredientUseCase,
                    getFavoriteMealListUseCase,
                    cydDispatchers,
                )

            viewModel.uiState.test {
                viewModel.loadMeals(MealType.Category("Beef"))
                advanceUntilIdle()
                assertTrue(expectMostRecentItem() is UiState.NoData)
            }
        }
}
