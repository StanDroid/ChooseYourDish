package com.cyd.feature.meal_details.viewmodel

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.viewModelScope
import com.cyd.base.model.Meal
import com.cyd.base.utils.ErrorMessage
import com.cyd.base.viewmodel.BaseViewModel
import com.cyd.feature.meal_details.usecase.GetMealDetailsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 *
 * This is derived from [MealDetailsViewModelState], but split into two possible subclasses to more
 * precisely represent the state available to render the UI.
 */
sealed interface MealDetailsUiState {

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
    ) : MealDetailsUiState

    /**
     * There are data to render, as contained in [errorMessages].
     */
    data class HasData(
        val data: Meal,
        override val isLoading: Boolean,
        override val errorMessages: List<ErrorMessage>,
    ) : MealDetailsUiState
}


/**
 * A representation of the route state, in a raw form
 */
 data class MealDetailsViewModelState(
    val data: Meal? = null,
    val isLoading: Boolean = false,
    val errorMessages: List<ErrorMessage> = emptyList(),
) {

    /**
     * Converts this [MealDetailsViewModelState] into a more strongly typed [MealDetailsUiState] for driving
     * the ui.
     */
    fun toUiState(): MealDetailsUiState =
        if (data == null) {
            MealDetailsUiState.NoData(
                isLoading = isLoading,
                errorMessages = errorMessages
            )
        } else {
            MealDetailsUiState.HasData(
                data = data,
                isLoading = isLoading,
                errorMessages = errorMessages,
            )
        }
}

@HiltViewModel
class MealDetailsViewModel @Inject constructor(
    private val useCase: GetMealDetailsUseCase
) : BaseViewModel() {

    private val viewModelState: MutableState<MealDetailsViewModelState> =
        mutableStateOf(MealDetailsViewModelState())

    val uiState: State<MealDetailsViewModelState> = viewModelState

    fun loadMealDetails(id: String) {
        viewModelState.value = MealDetailsViewModelState(isLoading = true)
        viewModelScope.launch {
            try {
                viewModelState.value = MealDetailsViewModelState(
                    data = useCase.execute(id),
                    isLoading = false
                )
            } catch (ex: Exception) {
                ex.printStackTrace()
                Log.e("TAG", "loadCategories failure $id")
                viewModelState.value = MealDetailsViewModelState(
                    isLoading = false, errorMessages =
                    listOf(ErrorMessage(ex.hashCode(), ex.stackTrace.toString()))
                )
            }
        }
    }
}
