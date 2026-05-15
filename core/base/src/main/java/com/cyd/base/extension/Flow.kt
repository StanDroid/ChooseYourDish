package com.cyd.base.extension

import com.cyd.base.CydDispatchers
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.mapLatest
import kotlinx.coroutines.flow.stateIn

@OptIn(ExperimentalCoroutinesApi::class)
fun <T, M> StateFlow<T>.mapLatest(
    coroutineScope: CoroutineScope,
    cydDispatchers: CydDispatchers,
    mapper: (value: T) -> M,
): StateFlow<M> =
    mapLatest { mapper(it) }
        .flowOn(cydDispatchers.io)
        .stateIn(
            coroutineScope,
            SharingStarted.Eagerly,
            mapper(value),
        )
