package com.cyd.data.aichat

interface AiChatDataSource {
    suspend fun sendMessage(message: String): Result<String>
}