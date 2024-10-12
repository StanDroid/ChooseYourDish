package com.cyd.search

import androidx.lifecycle.viewModelScope
import com.cyd.base.model.Ingredient
import com.cyd.base.usecase.execute
import com.cyd.base.utils.ErrorMessage
import com.cyd.base.viewmodel.BaseViewModel
import com.cyd.search.usecase.GetAllIngredientsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val useCase: GetAllIngredientsUseCase
) : BaseViewModel() {

    private val _viewModelState = MutableStateFlow(SearchViewModelState())
    val searchViewModelState = _viewModelState.asStateFlow()

    init {
        loadIngredients()
        subscribeToSearch()
    }

    private fun subscribeToSearch() {
        viewModelScope.launch {
            searchViewModelState
                .filter { state -> state.isSearching }
                .map { state ->
                    if (state.searchText.isBlank()) {
                        state.initialList
                    } else {
                        state.initialList.filter { item ->
                            item.name.uppercase().contains(state.searchText.trim().uppercase())
                        }
                    }
                }.stateIn(
                    scope = viewModelScope,
                    started = SharingStarted.WhileSubscribed(5000),
                    initialValue = searchViewModelState.value.initialList,
                ).collectLatest {
                    _viewModelState.value = _viewModelState.value.copy(list = it)
                }
        }
    }

    private fun loadIngredients() {
        viewModelScope.launch {
            val ingredientList = useCase.execute()
            _viewModelState.value = _viewModelState.value.copy(
                initialList = ingredientList
            )
        }
    }

    fun onSearchTextChange(text: String) {
        _viewModelState.value = _viewModelState.value.copy(
            searchText = text
        )
    }

    fun onItemClick(item: Ingredient) {
        _viewModelState.value = _viewModelState.value.copy(
            searchText = item.name, isSearching = false
        )
    }

    fun onToggleSearch() {
        _viewModelState.value = _viewModelState.value.copy(
            isSearching = _viewModelState.value.isSearching.not(),
        )
        if (!_viewModelState.value.isSearching) {
            onSearchTextChange("")
        }
    }

    data class SearchViewModelState(
        val initialList: List<Ingredient> = emptyList(),
        val list: List<Ingredient> = emptyList(),
        val searchText: String = "",
        val isLoading: Boolean = false,
        val isSearching: Boolean = false,
        val errorMessages: List<ErrorMessage> = emptyList(),
    ) {
        fun hasSelectedIngredient() = searchText.isNotEmpty() && !isSearching
    }
}

