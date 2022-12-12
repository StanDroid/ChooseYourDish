package com.cyd.feature.meals_by_category.viewmodel

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.viewModelScope
import com.cyd.core.data.model.mealdb.MealItem
import com.cyd.core.data.viewmodel.BaseViewModel
import com.cyd.core.utils.ErrorMessage
import com.cyd.feature.meals_by_category.usecase.GetMealListUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

sealed interface MealListUiState {
    val isLoading: Boolean
    val errorMessages: List<ErrorMessage>

    /**
     * There are no data to render.
     *
     * This could either be because they are still loading or they failed to load, and we are
     * waiting to reload them.
     */
    data class NoData(
        override val isLoading: Boolean,
        override val errorMessages: List<ErrorMessage>
    ) : MealListUiState

    /**
     * There are data to render, as contained in [list].
     */
    data class HasData(
        val list: List<MealItem>,
        override val isLoading: Boolean,
        override val errorMessages: List<ErrorMessage>,
    ) : MealListUiState
}


/**
 * A representation of the route state, in a raw form
 */
data class MealListViewModelState(
    val list: List<MealItem>? = null,
    val isLoading: Boolean = false,
    val errorMessages: List<ErrorMessage> = emptyList(),
) {

    fun toUiState(): MealListUiState =
        if (list == null) {
            MealListUiState.NoData(
                isLoading = isLoading,
                errorMessages = errorMessages
            )
        } else {
            MealListUiState.HasData(
                list = list,
                isLoading = isLoading,
                errorMessages = errorMessages,
            )
        }
}

@HiltViewModel
class MealListViewModel @Inject constructor(
    private val useCase: GetMealListUseCase
) : BaseViewModel() {

    private val viewModelState: MutableState<MealListViewModelState> =
        mutableStateOf(MealListViewModelState())

    val uiState: State<MealListViewModelState> = viewModelState

    fun loadMealsByCategory(name: String) {
        Log.e("TAG", "loadMealsByCategory started")
        viewModelState.value = MealListViewModelState(isLoading = true)
        viewModelScope.launch {
            try {
                val list = useCase.execute(name)
                Log.e("TAG", "loadMealsByCategory success: $list")
                viewModelState.value =
                    MealListViewModelState(list = list, isLoading = false)
            } catch (ex: Exception) {
                ex.printStackTrace()
                Log.e("TAG", "loadMealsByCategory failure")
                viewModelState.value = MealListViewModelState(
                    isLoading = false, errorMessages =
                    listOf(ErrorMessage(ex.hashCode(), ex.stackTrace.toString()))
                )
            }
        }
    }
}
