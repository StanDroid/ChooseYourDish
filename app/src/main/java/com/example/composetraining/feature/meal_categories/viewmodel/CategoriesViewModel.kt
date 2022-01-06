package com.example.composetraining.feature.meal_categories.viewmodel

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.MutableLiveData
import com.example.composetraining.core.data.model.mealdb.Category
import com.example.composetraining.core.data.usecase.execute
import com.example.composetraining.core.data.viewmodel.BaseViewModel
import com.example.composetraining.core.utils.ErrorMessage
import com.example.composetraining.feature.meal_categories.usecase.GetMealCategoriesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

/**
 *
 * This is derived from [CategoriesViewModelState], but split into two possible subclasses to more
 * precisely represent the state available to render the UI.
 */
sealed interface CategoriesUiState {

    val isLoading: Boolean
    val errorMessages: List<ErrorMessage>

    /**
     * There are no data to render.
     *
     * This could either be because they are still loading or they failed to load, and we are
     * waiting to reload them.
     */
    data class NoCategories(
        override val isLoading: Boolean,
        override val errorMessages: List<ErrorMessage>
    ) : CategoriesUiState

    /**
     * There are data to render, as contained in [list].
     */
    data class HasCategories(
        val list: List<Category>,
        override val isLoading: Boolean,
        override val errorMessages: List<ErrorMessage>,
    ) : CategoriesUiState
}


/**
 * A representation of the route state, in a raw form
 */
 data class CategoriesViewModelState(
    val list: List<Category>? = null,
    val isLoading: Boolean = false,
    val errorMessages: List<ErrorMessage> = emptyList(),
) {

    /**
     * Converts this [CategoriesViewModelState] into a more strongly typed [CategoriesUiState] for driving
     * the ui.
     */
    fun toUiState(): CategoriesUiState =
        if (list == null) {
            CategoriesUiState.NoCategories(
                isLoading = isLoading,
                errorMessages = errorMessages
            )
        } else {
            CategoriesUiState.HasCategories(
                list = list,
                isLoading = isLoading,
                errorMessages = errorMessages,
            )
        }
}

@HiltViewModel
class CategoriesViewModel @Inject constructor(
    private val useCase: GetMealCategoriesUseCase
) : BaseViewModel() {

    val categories = MutableLiveData<CategoriesViewModelState>()

    private val viewModelState: MutableState<CategoriesViewModelState> =
        mutableStateOf(CategoriesViewModelState(isLoading = true))

    val uiState: State<CategoriesViewModelState> = viewModelState

    init {
        loadCategories()
    }

    fun loadCategories() {
        Log.e("TAG", "loadCategories started")
        useCase.execute()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ list ->
                Log.e("TAG", "loadCategories success: $list")
                categories.value =
                    CategoriesViewModelState(list = list, isLoading = false)
            }, {
                it.printStackTrace()
                Log.e("TAG", "loadCategories failure")
                viewModelState.value = CategoriesViewModelState(
                    isLoading = false, errorMessages =
                    listOf(ErrorMessage(it.hashCode(), it.stackTrace.toString()))
                )
            }).run {
                compositeDisposable.add(this)
            }
    }
}
