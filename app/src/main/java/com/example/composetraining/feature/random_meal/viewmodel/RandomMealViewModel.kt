package com.example.composetraining.feature.random_meal.viewmodel

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import com.example.composetraining.core.data.model.mealdb.RandomMeal
import com.example.composetraining.core.data.usecase.execute
import com.example.composetraining.core.data.viewmodel.BaseViewModel
import com.example.composetraining.core.utils.ErrorMessage
import com.example.composetraining.feature.random_meal.usecase.GetRandomMealUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject


/**
 * UI state for the Home route.
 *
 * This is derived from [HomeViewModelState], but split into two possible subclasses to more
 * precisely represent the state available to render the UI.
 */
sealed interface HomeUiState {

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
        override val errorMessages: List<ErrorMessage>
    ) : HomeUiState

    /**
     * There are data to render, as contained in [randomMeal].
     */
    data class HasRandomMeal(
        val randomMeal: RandomMeal,
        override val isLoading: Boolean,
        override val errorMessages: List<ErrorMessage>,
    ) : HomeUiState
}


/**
 * An internal representation of the Home route state, in a raw form
 */
 data class HomeViewModelState(
    val randomMeal: RandomMeal? = null,
    val isLoading: Boolean = false,
    val errorMessages: List<ErrorMessage> = emptyList(),
    val searchInput: String = "",
) {

    /**
     * Converts this [HomeViewModelState] into a more strongly typed [HomeUiState] for driving
     * the ui.
     */
    fun toUiState(): HomeUiState =
        if (randomMeal == null) {
            HomeUiState.NoRandomMeal(
                isLoading = isLoading,
                errorMessages = errorMessages
            )
        } else {
            HomeUiState.HasRandomMeal(
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

    private val viewModelState: MutableState<HomeViewModelState> =
        mutableStateOf(HomeViewModelState(isLoading = true))

    val uiState: State<HomeViewModelState> = viewModelState

    init {
        loadRandomMeal()
    }

    fun loadRandomMeal() {
        Log.e("TAG", "loadRandomMeal started")
        useCase.execute()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ randomMeal ->
                Log.e("TAG", "loadRandomMeal success: $randomMeal")
                viewModelState.value =
                    HomeViewModelState(randomMeal = randomMeal, isLoading = false)
            }, {
                it.printStackTrace()
                Log.e("TAG", "loadRandomMeal failure")
                viewModelState.value = HomeViewModelState(
                    isLoading = false, errorMessages =
                    listOf(ErrorMessage(it.hashCode(), it.stackTrace.toString()))
                )
            }).run {
                compositeDisposable.add(this)
            }
    }
}
