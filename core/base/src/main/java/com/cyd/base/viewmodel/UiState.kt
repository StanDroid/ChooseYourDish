package com.cyd.base.viewmodel

import com.cyd.base.utils.ErrorMessage

sealed interface UiState<out T> {
    data class HasData<T>(
        val data: T,
    ) : UiState<T>

    data class NoData(
        val errorMessages: List<ErrorMessage>,
    ) : UiState<Nothing>

    data object InProgress : UiState<Nothing>
}
