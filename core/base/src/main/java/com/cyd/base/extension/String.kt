package com.cyd.base.extension

inline fun String?.ifNotNullOrEmpty(action: (String) -> Unit) {
    if (!this.isNullOrEmpty()) {
        action(this)
    }
}
