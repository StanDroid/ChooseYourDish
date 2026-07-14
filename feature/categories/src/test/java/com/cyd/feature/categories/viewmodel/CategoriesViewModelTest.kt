package com.cyd.feature.categories.viewmodel

import app.cash.turbine.test
import com.cyd.base.model.Category
import com.cyd.base.utils.ErrorHandler
import com.cyd.base.viewmodel.UiState
import com.cyd.domain.categories.GetMealCategoriesUseCase
import com.cyd.testing.MainDispatcherRule
import com.cyd.testing.TestCydDispatchers
import io.mockk.Runs
import io.mockk.coEvery
import io.mockk.every
import io.mockk.just
import io.mockk.mockk
import io.mockk.mockkObject
import io.mockk.unmockkAll
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class CategoriesViewModelTest {
    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    private val useCase: GetMealCategoriesUseCase = mockk()
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
    fun `when viewmodel initialized then loadCategories is called and uiState reflects success`() =
        runTest {
            val mockCategories =
                listOf(
                    Category("1", "Beef", "beef.jpg", "Beef descriptions"),
                    Category("2", "Chicken", "chicken.jpg", "Chicken descriptions"),
                )
            coEvery { useCase.execute(null) } returns mockCategories

            val viewModel = CategoriesViewModel(useCase, cydDispatchers)

            viewModel.uiState.test {
                mainDispatcherRule.testDispatcher.scheduler.advanceUntilIdle()
                val state = expectMostRecentItem()
                assertTrue(state is UiState.HasData)
                assertEquals(mockCategories, (state as UiState.HasData).data)
            }
        }

    @Test
    fun `when loadCategories throws exception then uiState reflects error`() =
        runTest {
            coEvery { useCase.execute(null) } throws RuntimeException("Network error")

            val viewModel = CategoriesViewModel(useCase, cydDispatchers)

            viewModel.uiState.test {
                mainDispatcherRule.testDispatcher.scheduler.advanceUntilIdle()
                assertTrue(expectMostRecentItem() is UiState.NoData)
            }
        }
}
