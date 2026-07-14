package com.cyd.base.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cyd.base.utils.ErrorHandler
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlin.coroutines.CoroutineContext
import kotlin.coroutines.cancellation.CancellationException

open class BaseViewModel :
    ViewModel(),
    CoroutineScope {
    private val coroutineExceptionHandler =
        CoroutineExceptionHandler { _, throwable ->
            if (throwable !is CancellationException) {
                ErrorHandler.printStackTrace(throwable)
                handleException(throwable)
            }
        }

    open fun handleException(throwable: Throwable?) {
    }

    override val coroutineContext: CoroutineContext
        get() = viewModelScope.coroutineContext + coroutineExceptionHandler
}
