package com.cyd.feature.randommeal.viewmodel

import app.cash.turbine.test
import com.cyd.base.model.RandomMeal
import com.cyd.base.usecase.execute
import com.cyd.base.utils.ErrorHandler
import com.cyd.base.viewmodel.UiState
import com.cyd.domain.meal.random.GetRandomMealUseCase
import com.cyd.testing.MainDispatcherRule
import com.cyd.testing.TestCydDispatchers
import io.mockk.Runs
import io.mockk.coEvery
import io.mockk.every
import io.mockk.just
import io.mockk.mockk
import io.mockk.mockkObject
import io.mockk.unmockkAll
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class RandomMealViewModelTest {
    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    private val useCase: GetRandomMealUseCase = mockk()
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
    fun `when viewmodel initialized then loadRandomMeal is called and uiState reflects success`() =
        runTest {
            val mockRandomMeal =
                RandomMeal("1", "Pancakes", "American", "Breakfast", "instructions", "thumb", "youtube", "source")
            coEvery { useCase.execute() } returns mockRandomMeal

            val viewModel = RandomMealViewModel(useCase, cydDispatchers)

            viewModel.uiState.test {
                mainDispatcherRule.testDispatcher.scheduler.advanceUntilIdle()
                val state = expectMostRecentItem()
                assertTrue(state is UiState.HasData)
                assertEquals(mockRandomMeal, (state as UiState.HasData).data)
            }
        }

    @Test
    fun `when onLoadNextRandomMealClick then uiState reflects new random meal`() =
        runTest {
            val mockRandomMeal1 =
                RandomMeal("1", "Pancakes", "American", "Breakfast", "instructions", "thumb", "youtube", "source")
            val mockRandomMeal2 =
                RandomMeal("2", "Waffles", "American", "Breakfast", "instructions", "thumb", "youtube", "source")
            coEvery { useCase.execute() } returns mockRandomMeal1 andThen mockRandomMeal2

            val viewModel = RandomMealViewModel(useCase, cydDispatchers)

            viewModel.uiState.test {
                mainDispatcherRule.testDispatcher.scheduler.advanceUntilIdle()
                expectMostRecentItem()

                viewModel.onLoadNextRandomMealClick()
                mainDispatcherRule.testDispatcher.scheduler.advanceUntilIdle()

                val state = expectMostRecentItem()
                assertTrue(state is UiState.HasData)
                assertEquals(mockRandomMeal2, (state as UiState.HasData).data)
            }
        }

    @Test
    fun `when useCase throws exception then uiState reflects error`() =
        runTest {
            coEvery { useCase.execute() } throws RuntimeException("Network error")

            val viewModel = RandomMealViewModel(useCase, cydDispatchers)

            viewModel.uiState.test {
                advanceUntilIdle()
                assertTrue(expectMostRecentItem() is UiState.NoData)
            }
        }
}
