package com.cyd.search.viewmodel.viewstate

import com.cyd.base.model.Ingredient
import com.cyd.base.utils.ErrorMessage

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
