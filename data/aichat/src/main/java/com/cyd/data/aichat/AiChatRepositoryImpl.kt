package com.cyd.data.aichat

import com.cyd.base.CydDispatchers
import com.cyd.domain.aichat.AiChatRepository
import kotlinx.coroutines.withContext
import javax.inject.Inject

class AiChatRepositoryImpl
    @Inject
    constructor(
        private val aiChatDataSource: AiChatDataSource,
        private val cydDispatchers: CydDispatchers,
    ) : AiChatRepository {
        override suspend fun sendMessage(message: String): Result<String> =
            withContext(cydDispatchers.io) {
                aiChatDataSource.sendMessage(message)
            }
    }