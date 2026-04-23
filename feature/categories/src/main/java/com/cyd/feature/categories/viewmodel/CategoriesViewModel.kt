package com.cyd.feature.categories.viewmodel

import androidx.lifecycle.viewModelScope
import com.cyd.base.extension.mapLatest
import com.cyd.base.model.Category
import com.cyd.base.usecase.execute
import com.cyd.base.utils.ErrorMessage
import com.cyd.base.viewmodel.BaseViewModel
import com.cyd.base.viewmodel.UiState
import com.cyd.base.viewmodel.ViewState
import com.cyd.feature.categories.usecase.GetMealCategoriesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CategoriesViewModel
@Inject
constructor(
    private val useCase: GetMealCategoriesUseCase,
) : BaseViewModel() {
    private val viewModelState: MutableStateFlow<ViewState<List<Category>>> =
        MutableStateFlow(ViewState(isLoading = true))

    val uiState: StateFlow<UiState<List<Category>>> =
        viewModelState
            .mapLatest(viewModelScope) { it.toUiState() }

    init {
        loadCategories()
    }

    private fun loadCategories() {
        launch {
            val categories = useCase.execute()
            viewModelState.update {
                it.copy(data = categories, isLoading = false)
            }
        }
    }

    override fun handleException(throwable: Throwable?) {
        super.handleException(throwable)
        throwable?.let { error ->
            error.printStackTrace()
            viewModelState.update {
                it.copy(
                    isLoading = false,
                    errorMessages =
                        listOf(
                            ErrorMessage(
                                it.hashCode(),
                                error
                                    .stackTrace
                                    .toString(),
                            ),
                        ),
                )
            }
        }
    }
}
