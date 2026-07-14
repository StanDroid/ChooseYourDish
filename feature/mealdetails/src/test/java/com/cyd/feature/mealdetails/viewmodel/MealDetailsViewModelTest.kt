package com.cyd.feature.mealdetails.viewmodel

import app.cash.turbine.test
import com.cyd.base.model.Meal
import com.cyd.base.utils.ErrorHandler
import com.cyd.base.viewmodel.UiState
import com.cyd.domain.meal.details.GetMealDetailsUseCase
import com.cyd.domain.meal.details.MakeMealAsFavoriteUseCase
import com.cyd.domain.meal.details.RemoveMealFromFavoritesUseCase
import com.cyd.testing.MainDispatcherRule
import com.cyd.testing.TestCydDispatchers
import io.mockk.Runs
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.every
import io.mockk.just
import io.mockk.mockk
import io.mockk.mockkObject
import io.mockk.unmockkAll
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class MealDetailsViewModelTest {
    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    private val useCase: GetMealDetailsUseCase = mockk()
    private val makeMealAsFavoriteUseCase: MakeMealAsFavoriteUseCase = mockk()
    private val removeMealFromFavoritesUseCase: RemoveMealFromFavoritesUseCase = mockk()
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
    fun `when loadMealDetails then uiState reflects success`() =
        runTest {
            val mockMeal = Meal("1", "Pancakes", "url", isFavorite = false)
            coEvery { useCase.execute("1") } returns mockMeal
            val viewModel =
                MealDetailsViewModel(useCase, makeMealAsFavoriteUseCase, removeMealFromFavoritesUseCase, cydDispatchers)

            viewModel.uiState.test {
                viewModel.loadMealDetails("1")
                mainDispatcherRule.testDispatcher.scheduler.advanceUntilIdle()

                val state = expectMostRecentItem()
                assertTrue(state is UiState.HasData)
                assertEquals(mockMeal, (state as UiState.HasData).data)
            }
        }

    @Test
    fun `when tapOnFavorite and is not favorite then make as favorite is called and uiState is updated`() =
        runTest {
            val mockMeal = Meal("1", "Pancakes", "url", isFavorite = false)
            coEvery { useCase.execute("1") } returns mockMeal
            coEvery { makeMealAsFavoriteUseCase.execute(mockMeal) } returns Unit
            val viewModel =
                MealDetailsViewModel(useCase, makeMealAsFavoriteUseCase, removeMealFromFavoritesUseCase, cydDispatchers)

            viewModel.uiState.test {
                viewModel.loadMealDetails("1")
                mainDispatcherRule.testDispatcher.scheduler.advanceUntilIdle()
                expectMostRecentItem()

                viewModel.tapOnFavorite()
                mainDispatcherRule.testDispatcher.scheduler.advanceUntilIdle()

                val state = expectMostRecentItem()
                assertTrue(state is UiState.HasData)
                assertTrue((state as UiState.HasData).data.isFavorite)
                coVerify { makeMealAsFavoriteUseCase.execute(mockMeal) }
                coVerify(exactly = 0) { removeMealFromFavoritesUseCase.execute(any()) }
            }
        }

    @Test
    fun `when tapOnFavorite and is favorite then remove from favorites is called and uiState is updated`() =
        runTest {
            val mockMeal = Meal("1", "Pancakes", "url", isFavorite = true)
            coEvery { useCase.execute("1") } returns mockMeal
            coEvery { removeMealFromFavoritesUseCase.execute(mockMeal) } returns Unit
            val viewModel =
                MealDetailsViewModel(useCase, makeMealAsFavoriteUseCase, removeMealFromFavoritesUseCase, cydDispatchers)

            viewModel.uiState.test {
                viewModel.loadMealDetails("1")
                mainDispatcherRule.testDispatcher.scheduler.advanceUntilIdle()
                expectMostRecentItem()

                viewModel.tapOnFavorite()
                mainDispatcherRule.testDispatcher.scheduler.advanceUntilIdle()

                val state = expectMostRecentItem()
                assertTrue(state is UiState.HasData)
                assertFalse((state as UiState.HasData).data.isFavorite)
                coVerify { removeMealFromFavoritesUseCase.execute(mockMeal) }
                coVerify(exactly = 0) { makeMealAsFavoriteUseCase.execute(any()) }
            }
        }

    @Test
    fun `when loadMealDetails throws exception then uiState reflects error`() =
        runTest {
            coEvery { useCase.execute("1") } throws RuntimeException("Network error")
            val viewModel =
                MealDetailsViewModel(useCase, makeMealAsFavoriteUseCase, removeMealFromFavoritesUseCase, cydDispatchers)

            viewModel.uiState.test {
                viewModel.loadMealDetails("1")
                mainDispatcherRule.testDispatcher.scheduler.advanceUntilIdle()

                assertTrue(expectMostRecentItem() is UiState.NoData)
            }
        }
}
