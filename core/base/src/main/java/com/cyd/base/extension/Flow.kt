package com.cyd.base.extension

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.mapLatest
import kotlinx.coroutines.flow.stateIn

fun <T, M> StateFlow<T>.map(
    coroutineScope: CoroutineScope,
    mapper: (value: T) -> M,
): StateFlow<M> =
    map { mapper(it) }.stateIn(
        coroutineScope,
        SharingStarted.Eagerly,
        mapper(value),
    )

@OptIn(ExperimentalCoroutinesApi::class)
fun <T, M> StateFlow<T>.mapLatest(
    coroutineScope: CoroutineScope,
    mapper: (value: T) -> M,
): StateFlow<M> =
    mapLatest { mapper(it) }
        .flowOn(Dispatchers.IO)
        .stateIn(
            coroutineScope,
            SharingStarted.Eagerly,
            mapper(value),
        )
