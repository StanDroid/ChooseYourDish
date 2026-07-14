package com.cyd.domain.aichat

interface AiChatRepository {
    suspend fun sendMessage(message: String): Result<String>
}