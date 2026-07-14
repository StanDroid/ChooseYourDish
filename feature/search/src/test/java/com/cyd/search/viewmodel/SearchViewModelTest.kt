package com.cyd.search.viewmodel

import app.cash.turbine.test
import com.cyd.base.model.Ingredient
import com.cyd.base.usecase.execute
import com.cyd.domain.ingredients.GetAllIngredientsUseCase
import com.cyd.testing.MainDispatcherRule
import com.cyd.testing.TestCydDispatchers
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.advanceTimeBy
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Rule
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class SearchViewModelTest {
    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    private val useCase: GetAllIngredientsUseCase = mockk()
    private val cydDispatchers = TestCydDispatchers(mainDispatcherRule.testDispatcher)

    private lateinit var viewModel: SearchViewModel

    @Test
    fun `when initialized then loadIngredients loads initial list`() =
        runTest {
            val mockIngredients =
                listOf(
                    Ingredient("1", "Chicken", "desc"),
                    Ingredient("2", "Beef", "desc"),
                )
            coEvery { useCase.execute() } returns mockIngredients

            viewModel = SearchViewModel(useCase, cydDispatchers)

            viewModel.viewModelState.test {
                val state = awaitItem() // Initial or loaded state
                val loadedState = if (state.initialList.isEmpty()) awaitItem() else state

                assertEquals(mockIngredients, loadedState.initialList)
                assertEquals(mockIngredients, loadedState.list)
            }
        }

    @Test
    fun `when onSearchTextChange then list is filtered`() =
        runTest {
            val mockIngredients =
                listOf(
                    Ingredient("1", "Chicken", "desc"),
                    Ingredient("2", "Beef", "desc"),
                )
            coEvery { useCase.execute() } returns mockIngredients

            viewModel = SearchViewModel(useCase, cydDispatchers)

            viewModel.viewModelState.test {
                awaitItem() // Skip initial states

                viewModel.onToggleSearch() // Ensure isSearching is true
                // if toggle makes it false, we need to toggle again.
                if (!viewModel.viewModelState.value.isSearching) {
                    viewModel.onToggleSearch()
                }

                viewModel.onSearchTextChange("bee")

                // Advance time because of SharingStarted.WhileSubscribed(5000) and flows
                advanceTimeBy(5000)

                val updatedState = expectMostRecentItem()
                assertEquals("bee", updatedState.searchText)
                assertEquals(1, updatedState.list.size)
                assertEquals("Beef", updatedState.list[0].name)
            }
        }

    @Test
    fun `when onItemClick then searchText is updated and isSearching is false`() =
        runTest {
            val mockIngredients =
                listOf(
                    Ingredient("1", "Chicken", "desc"),
                )
            coEvery { useCase.execute() } returns mockIngredients

            viewModel = SearchViewModel(useCase, cydDispatchers)

            viewModel.viewModelState.test {
                awaitItem() // Skip initial

                viewModel.onItemClick(mockIngredients[0])

                val updatedState = expectMostRecentItem()
                assertEquals("Chicken", updatedState.searchText)
                assertFalse(updatedState.isSearching)
            }
        }

    @Test
    fun `when onToggleSearch then isSearching is toggled and text is cleared if not searching`() =
        runTest {
            coEvery { useCase.execute() } returns emptyList()

            viewModel = SearchViewModel(useCase, cydDispatchers)

            viewModel.viewModelState.test {
                val initialState = expectMostRecentItem()
                val initialSearch = initialState.isSearching

                viewModel.onSearchTextChange("test")

                viewModel.onToggleSearch()

                val updatedState = expectMostRecentItem()
                assertEquals(!initialSearch, updatedState.isSearching)
                if (!updatedState.isSearching) {
                    assertEquals("", updatedState.searchText)
                }
            }
        }
}
