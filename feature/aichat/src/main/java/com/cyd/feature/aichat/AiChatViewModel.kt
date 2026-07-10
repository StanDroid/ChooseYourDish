package com.cyd.feature.aichat

import android.content.Context
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cyd.feature.aichat.model.ChatMessage
import com.cyd.feature.aichat.settings.SYSTEM_INSTRUCTION
import com.google.ai.client.generativeai.Chat
import com.google.ai.client.generativeai.GenerativeModel
import com.google.ai.client.generativeai.type.QuotaExceededException
import com.google.ai.client.generativeai.type.asTextOrNull
import com.google.ai.client.generativeai.type.content
import com.google.ai.client.generativeai.type.generationConfig
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AiChatViewModel
    @Inject
    constructor(
        private val context: Context,
    ) : ViewModel() {
        private val _uiState = MutableStateFlow(AiChatUiState())
        val uiState: StateFlow<AiChatUiState> = _uiState.asStateFlow()

        private var generativeModel: GenerativeModel? = null
        private var chatSession: Chat? = null

        fun initModel(apiKey: String) {
            if (generativeModel != null) return
            generativeModel =
                GenerativeModel(
                    modelName = "gemini-2.5-flash",
                    apiKey = apiKey,
                    generationConfig =
                        generationConfig {
                            temperature = 0.7f
                            maxOutputTokens = 1024
                        },
                    systemInstruction =
                        content {
                            text(SYSTEM_INSTRUCTION)
                        },
                )
            chatSession = generativeModel?.startChat()
        }

        fun sendMessage(userText: String) {
            val trimmed = userText.trim()
            if (trimmed.isBlank()) return

            val userMessage = ChatMessage(text = trimmed, isFromUser = true)
            val loadingMessage = ChatMessage(text = "", isFromUser = false, isLoading = true)

            _uiState.update { state ->
                state.copy(
                    messages = state.messages + userMessage + loadingMessage,
                    isLoading = true,
                    errorMessage = null,
                )
            }

            viewModelScope.launch {
                try {
                    val response = chatSession?.sendMessage(trimmed)
                    val responseText =
                        response
                            ?.candidates
                            ?.firstOrNull()
                            ?.content
                            ?.parts
                            ?.firstOrNull()
                            ?.asTextOrNull()
                            ?: context.getString(R.string.i_couldn_t_generate_a_response_please_try_again)

                    _uiState.update { state ->
                        val messages = state.messages.toMutableList()
                        // Replace loading bubble with real response
                        val loadingIndex = messages.indexOfLast { it.isLoading }
                        if (loadingIndex != -1) {
                            messages[loadingIndex] =
                                ChatMessage(
                                    text = responseText,
                                    isFromUser = false,
                                )
                        }
                        state.copy(messages = messages, isLoading = false)
                    }
                } catch (e: Exception) {
                    Log.e("TAG", "Ai Chat. Send Message Error: ${e.message}")
                    _uiState.update { state ->
                        val messages = state.messages.toMutableList()
                        val loadingIndex = messages.indexOfLast { it.isLoading }
                        if (loadingIndex != -1) {
                            messages[loadingIndex] =
                                ChatMessage(
                                    text = getError(e),
                                    isFromUser = false,
                                    isError = true,
                                )
                        }
                        state.copy(messages = messages, isLoading = false)
                    }
                }
            }
        }

        private fun getError(e: Exception): String =
            when (e) {
                is QuotaExceededException ->
                    e.message
                        ?: context.getString(R.string.sorry_i_m_having_trouble_right_now_please_try_again_later)
                else -> context.getString(R.string.sorry_something_went_wrong_please_check_your_connection_and_try_again)
            }
    }
