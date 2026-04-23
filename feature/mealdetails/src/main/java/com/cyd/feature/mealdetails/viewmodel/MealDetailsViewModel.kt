package com.cyd.feature.mealdetails.viewmodel

import android.util.Log
import androidx.lifecycle.viewModelScope
import com.cyd.base.extension.map
import com.cyd.base.model.Meal
import com.cyd.base.utils.ErrorMessage
import com.cyd.base.viewmodel.BaseViewModel
import com.cyd.base.viewmodel.UiState
import com.cyd.base.viewmodel.ViewState
import com.cyd.feature.mealdetails.usecase.GetMealDetailsUseCase
import com.cyd.feature.mealdetails.usecase.MakeMealAsFavoriteUseCase
import com.cyd.feature.mealdetails.usecase.RemoveMealFromFavoritesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MealDetailsViewModel
@Inject
constructor(
    private val useCase: GetMealDetailsUseCase,
    private val makeMealAsFavoriteUseCase: MakeMealAsFavoriteUseCase,
    private val removeMealFromFavoritesUseCase: RemoveMealFromFavoritesUseCase,
) : BaseViewModel() {
    private val viewModelState: MutableStateFlow<ViewState<Meal>> =
        MutableStateFlow(ViewState(isLoading = true))

    val uiState: StateFlow<UiState<Meal>> =
        viewModelState
            .map(viewModelScope) { it.toUiState() }

    fun loadMealDetails(id: String) {
        launch {
            viewModelState.update {
                it.copy(
                    data = useCase.execute(id),
                    isLoading = false,
                )
            }
        }
    }

    override fun handleException(throwable: Throwable?) {
        super.handleException(throwable)
        throwable?.printStackTrace()
        Log.e("CYD", "loadCategories failure: $throwable")
        viewModelState.update {
            it.copy(
                isLoading = false,
                errorMessages =
                    listOf(
                        ErrorMessage(
                            throwable.hashCode(),
                            throwable?.message.orEmpty(),
                        ),
                    ),
            )
        }
    }

    fun tapOnFavorite() {
        launch {
            viewModelState.value.data?.let {
                if (it.isFavorite) {
                    removeMealFromFavoritesUseCase.execute(it)
                } else {
                    makeMealAsFavoriteUseCase.execute(it)
                }
                viewModelState.update {
                    it.copy(
                        data = it.data?.copy(isFavorite = (it.data?.isFavorite ?: false).not()),
                    )
                }
            }
        }
    }
}
