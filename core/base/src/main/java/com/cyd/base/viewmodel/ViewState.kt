package com.cyd.base.viewmodel

import com.cyd.base.utils.ErrorMessage

data class ViewState<out T>(
    val data: T? = null,
    val isLoading: Boolean = false,
    val errorMessages: List<ErrorMessage> = emptyList(),
) {
    fun toUiState(): UiState<T> =
        when {
            isLoading -> UiState.InProgress
            data != null -> UiState.HasData(data = data)
            else -> UiState.NoData(errorMessages = errorMessages)
        }
}
