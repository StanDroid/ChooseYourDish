package com.cyd.feature.random_meal.viewmodel

import android.util.Log
import androidx.lifecycle.viewModelScope
import com.cyd.core.data.model.mealdb.RandomMeal
import com.cyd.core.data.usecase.execute
import com.cyd.core.data.viewmodel.BaseViewModel
import com.cyd.core.extension.map
import com.cyd.core.utils.ErrorMessage
import com.cyd.feature.random_meal.usecase.GetRandomMealUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject


/**
 * This is derived from [RandomMealViewModelState], but split into two possible subclasses to more
 * precisely represent the state available to render the UI.
 */
sealed interface RandomMealUiState {
    val isLoading: Boolean
    val errorMessages: List<ErrorMessage>

    /**
     * There are no data to render.
     *
     * This could either be because they are still loading or they failed to load, and we are
     * waiting to reload them.
     */
    data class NoRandomMeal(
        override val isLoading: Boolean,
        override val errorMessages: List<ErrorMessage> = listOf()
    ) : RandomMealUiState

    /**
     * There are data to render, as contained in [randomMeal].
     */
    data class HasRandomMeal(
        val randomMeal: RandomMeal,
        override val isLoading: Boolean,
        override val errorMessages: List<ErrorMessage>,
    ) : RandomMealUiState
}

data class RandomMealViewModelState(
    val randomMeal: RandomMeal? = null,
    val isLoading: Boolean = false,
    val errorMessages: List<ErrorMessage> = emptyList(),
    val searchInput: String = "",
) {

    /**
     * Converts this [RandomMealViewModelState] into a more strongly typed [RandomMealUiState] for driving
     * the ui.
     */
    fun toUiState(): RandomMealUiState =
        if (randomMeal == null) {
            RandomMealUiState.NoRandomMeal(
                isLoading = isLoading,
                errorMessages = errorMessages
            )
        } else {
            RandomMealUiState.HasRandomMeal(
                randomMeal = randomMeal,
                isLoading = isLoading,
                errorMessages = errorMessages,
            )
        }
}

@HiltViewModel
class RandomMealViewModel @Inject constructor(
    private val useCase: GetRandomMealUseCase
) : BaseViewModel() {

    private val viewModelState: MutableStateFlow<RandomMealViewModelState> =
        MutableStateFlow(RandomMealViewModelState(isLoading = true))

    val uiState: StateFlow<RandomMealUiState> =
        viewModelState.map(viewModelScope) { it.toUiState() }

    init {
        loadRandomMeal()
    }

    fun onLoadNextRandomMealClick() {
        loadRandomMeal()
    }

    private fun loadRandomMeal() {
        Log.e("TAG", "loadRandomMeal started")
        viewModelState.update { RandomMealViewModelState(isLoading = true) }
        viewModelScope.launch {
            try {
                val randomMeal = useCase.execute()
                viewModelState.update {
                    RandomMealViewModelState(randomMeal = randomMeal, isLoading = false)
                }
                Log.e("TAG", "loadRandomMeal success: $randomMeal")
            } catch (it: Exception) {
                it.printStackTrace()
                Log.e("TAG", "loadRandomMeal failure")
                viewModelState.update { _ ->
                    RandomMealViewModelState(
                        isLoading = false, errorMessages =
                        listOf(ErrorMessage(it.hashCode(), it.stackTrace.toString()))
                    )
                }
            }
        }
    }
}
