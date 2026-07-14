package com.cyd.data.aichat

import com.cyd.base.utils.ErrorHandler
import com.cyd.data.aichat.config.AiConstants
import com.cyd.domain.aichat.AiChatException
import com.google.ai.client.generativeai.Chat
import com.google.ai.client.generativeai.GenerativeModel
import com.google.ai.client.generativeai.type.QuotaExceededException
import com.google.ai.client.generativeai.type.asTextOrNull
import com.google.ai.client.generativeai.type.content
import com.google.ai.client.generativeai.type.generationConfig
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GeminiAiChatClient
@Inject
constructor() {
    private val apiKey = BuildConfig.GEMINI_API_KEY.trim()
    private val mutex = Mutex()
    private var chatSession: Chat? = null

    val isConfigured: Boolean = apiKey.isNotBlank()

    suspend fun sendMessage(message: String): Result<String> {
        if (!isConfigured) {
            return Result.failure(AiChatException.BackendNotConfigured())
        }

        return mutex.withLock {
            try {
                val response = getChatSession().sendMessage(message)
                val reply =
                    response
                        .candidates
                        .firstOrNull()
                        ?.content
                        ?.parts
                        ?.firstOrNull()
                        ?.asTextOrNull()
                        ?.trim()
                        .orEmpty()

                if (reply.isBlank()) {
                    Result.failure(AiChatException.EmptyResponse())
                } else {
                    Result.success(reply)
                }
            } catch (e: QuotaExceededException) {
                Result.failure(AiChatException.RequestRejected())
            } catch (e: Exception) {
                ErrorHandler.printStackTrace(e)
                Result.failure(AiChatException.NetworkUnavailable())
            }
        }
    }

    private fun getChatSession(): Chat {
        val currentSession = chatSession
        if (currentSession != null) return currentSession

        val newSession =
            GenerativeModel(
                modelName = AiConstants.AI_MODEL,
                apiKey = apiKey,
                generationConfig =
                    generationConfig {
                        temperature = 0.7f
                        maxOutputTokens = 1024
                    },
                systemInstruction =
                    content {
                        text(AiConstants.SYSTEM_INSTRUCTION)
                    },
            ).startChat()

        chatSession = newSession
        return newSession
    }
}
