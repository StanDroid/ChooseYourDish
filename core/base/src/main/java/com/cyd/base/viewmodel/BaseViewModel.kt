package com.cyd.base.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlin.coroutines.CoroutineContext
import kotlin.coroutines.cancellation.CancellationException

open class BaseViewModel : ViewModel(), CoroutineScope {

    private val coroutineExceptionHandler = CoroutineExceptionHandler { _, throwable ->
        if (throwable is CancellationException) {
            Log.d("CYD", "Coroutine cancelled")
        } else {
            Log.e("CYD", "Coroutine exception: ${throwable.message}", throwable)
            handleException(throwable)
        }
    }

    open fun handleException(throwable: Throwable?) {
    }

    override val coroutineContext: CoroutineContext
        get() = viewModelScope.coroutineContext + coroutineExceptionHandler
}