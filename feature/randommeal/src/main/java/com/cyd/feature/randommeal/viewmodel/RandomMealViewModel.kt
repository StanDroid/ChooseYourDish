package com.cyd.feature.randommeal.viewmodel

import androidx.lifecycle.viewModelScope
import com.cyd.base.CydDispatchers
import com.cyd.base.extension.mapLatest
import com.cyd.base.model.RandomMeal
import com.cyd.base.usecase.execute
import com.cyd.base.utils.ErrorMessage
import com.cyd.base.viewmodel.BaseViewModel
import com.cyd.base.viewmodel.UiState
import com.cyd.base.viewmodel.ViewState
import com.cyd.domain.meal.random.GetRandomMealUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RandomMealViewModel
    @Inject
    constructor(
        private val useCase: GetRandomMealUseCase,
        private val cydDispatchers: CydDispatchers,
    ) : BaseViewModel() {
        private val viewModelState: MutableStateFlow<ViewState<RandomMeal>> =
            MutableStateFlow(ViewState(isLoading = true))

        val uiState: StateFlow<UiState<RandomMeal>> =
            viewModelState.mapLatest(
                viewModelScope,
                cydDispatchers,
            ) { it.toUiState() }

        init {
            loadRandomMeal()
        }

        fun onLoadNextRandomMealClick() {
            loadRandomMeal()
        }

        private fun loadRandomMeal() {
            launch {
                val randomMeal = useCase.execute()
                viewModelState.update {
                    it.copy(data = randomMeal, isLoading = false)
                }
            }
        }

        override fun handleException(throwable: Throwable?) {
            super.handleException(throwable)
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
