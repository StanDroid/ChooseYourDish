package com.cyd.feature.aichat

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cyd.base.utils.ErrorHandler
import com.cyd.domain.aichat.AiChatException
import com.cyd.domain.aichat.AiChatRepository
import com.cyd.feature.aichat.model.ChatMessage
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
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
        private val aiChatRepository: AiChatRepository,
        @param:ApplicationContext private val context: Context,
    ) : ViewModel() {
        private val _uiState = MutableStateFlow(AiChatUiState())
        val uiState: StateFlow<AiChatUiState> = _uiState.asStateFlow()

        fun sendMessage(userText: String) {
            val trimmed = userText.trim()
            if (trimmed.isBlank() || _uiState.value.isLoading) return

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
                val result = aiChatRepository.sendMessage(trimmed)

                result.fold(
                    onSuccess = { responseText -> replaceLoadingMessage(responseText) },
                    onFailure = { error ->
                        ErrorHandler.printStackTrace( "AI chat send message failed", error)
                        replaceLoadingMessage(
                            text = getErrorMessage(error),
                            isError = true,
                        )
                    },
                )
            }
        }

        private fun replaceLoadingMessage(
            text: String,
            isError: Boolean = false,
        ) {
            _uiState.update { state ->
                val messages = state.messages.toMutableList()
                val loadingIndex = messages.indexOfLast { it.isLoading }
                if (loadingIndex != -1) {
                    messages[loadingIndex] =
                        ChatMessage(
                            text = text,
                            isFromUser = false,
                            isError = isError,
                        )
                }
                state.copy(messages = messages, isLoading = false)
            }
        }

        private fun getErrorMessage(error: Throwable): String =
            when (error) {
                AiChatException.BackendNotConfigured() ->
                    context.getString(R.string.ai_chat_backend_not_configured)

                AiChatException.NetworkUnavailable() ->
                    context.getString(R.string.ai_chat_backend_unavailable)

                AiChatException.RequestRejected() ->
                    context.getString(R.string.ai_chat_request_rejected)

                AiChatException.ServerUnavailable() ->
                    context.getString(R.string.ai_chat_backend_problem)

                AiChatException.EmptyResponse() ->
                    context.getString(R.string.i_couldn_t_generate_a_response_please_try_again)

                else ->
                    context.getString(R.string.sorry_something_went_wrong_please_check_your_connection_and_try_again)
            }

        private companion object {
            const val TAG = "AiChatViewModel"
        }
    }