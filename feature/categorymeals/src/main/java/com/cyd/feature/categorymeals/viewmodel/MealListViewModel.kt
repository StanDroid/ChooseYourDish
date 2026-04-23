package com.cyd.feature.categorymeals.viewmodel

import android.util.Log
import androidx.lifecycle.viewModelScope
import com.cyd.base.extension.map
import com.cyd.base.model.MealItem
import com.cyd.base.utils.ErrorMessage
import com.cyd.base.viewmodel.BaseViewModel
import com.cyd.base.viewmodel.UiState
import com.cyd.base.viewmodel.ViewState
import com.cyd.feature.categorymeals.usecase.GetFavoriteMealListUseCase
import com.cyd.feature.categorymeals.usecase.GetMealListByIngredientUseCase
import com.cyd.feature.categorymeals.usecase.GetMealListUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MealListViewModel
@Inject
constructor(
    private val useCase: GetMealListUseCase,
    private val useCaseByIngredientUseCase: GetMealListByIngredientUseCase,
    private val getFavoriteMealListUseCase: GetFavoriteMealListUseCase,
) : BaseViewModel() {
    private val viewModelState: MutableStateFlow<ViewState<List<MealItem>>> =
        MutableStateFlow(ViewState(isLoading = true))

    val uiState: StateFlow<UiState<List<MealItem>>> =
        viewModelState
            .map(viewModelScope) { it.toUiState() }

    fun loadMeals(mealType: MealType) {
        launch {
            val list =
                when (mealType) {
                    is MealType.Category -> flowOf(useCase.execute(mealType.name))
                    is MealType.Ingredient -> flowOf(useCaseByIngredientUseCase.execute(mealType.name))
                    is MealType.Favorites -> getFavoriteMealListUseCase.execute(null)
                }
            list.collectLatest { result ->
                viewModelState.update { it.copy(data = result, isLoading = false) }
            }
        }
    }

    override fun handleException(throwable: Throwable?) {
        super.handleException(throwable)
        Log.e("CYD", "loadMealsByCategory failure: ${throwable?.message}")
        viewModelState.update {
            it.copy(
                isLoading = false,
                errorMessages =
                    listOf(
                        ErrorMessage(throwable.hashCode(), throwable?.message.orEmpty()),
                    ),
            )
        }
    }
}
