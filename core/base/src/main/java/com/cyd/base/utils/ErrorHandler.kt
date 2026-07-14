package com.cyd.base.utils

import android.util.Log
import com.cyd.base.BuildConfig

object ErrorHandler {
    fun printStackTrace(throwable: Throwable) {
        throwable.printStackTrace()
    }

    fun printStackTrace(message: String, throwable: Throwable) {
        if (BuildConfig.DEBUG) {
            Log.e(TAG, message, throwable)
        }
        printStackTrace(throwable)
    }

    private const val TAG = "ErrorHandler"
}
