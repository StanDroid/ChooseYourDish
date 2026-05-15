package com.cyd.core

import com.cyd.base.CydDispatchers
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CydDispatchersImpl
    @Inject
    constructor() : CydDispatchers {
        override val default = Dispatchers.Default
        override val io = Dispatchers.IO
        override val main = Dispatchers.Main
        override val mainImmediate = Dispatchers.Main.immediate
        override val unconfined = Dispatchers.Unconfined
    }
