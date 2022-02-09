package com.example.composetraining.feature.meal_details.viewmodel

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import com.example.composetraining.core.data.model.mealdb.Category
import com.example.composetraining.core.data.model.mealdb.Meal
import com.example.composetraining.core.data.usecase.execute
import com.example.composetraining.core.data.viewmodel.BaseViewModel
import com.example.composetraining.core.utils.ErrorMessage
import com.example.composetraining.feature.meal_details.usecase.GetMealDetailsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

/**
 *
 * This is derived from [CategoriesViewModelState], but split into two possible subclasses to more
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
     * There are data to render, as contained in [list].
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
     * Converts this [CategoriesViewModelState] into a more strongly typed [CategoriesUiState] for driving
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
        Log.e("TAG", "loadMealDetails started")
        viewModelState.value = MealDetailsViewModelState(isLoading = true)
        useCase.execute(id)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ data ->
                Log.e("TAG", "loadCategories success: $data")
                viewModelState.value =
                    MealDetailsViewModelState(data = data, isLoading = false)
            }, {
                it.printStackTrace()
                Log.e("TAG", "loadCategories failure")
                viewModelState.value = MealDetailsViewModelState(
                    isLoading = false, errorMessages =
                    listOf(ErrorMessage(it.hashCode(), it.stackTrace.toString()))
                )
            }).run(compositeDisposable::add)
    }
}
