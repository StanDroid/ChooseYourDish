package com.cyd.feature.aichat

import com.cyd.feature.aichat.model.ChatMessage

data class AiChatUiState(
    val messages: List<ChatMessage> =
        listOf(
            ChatMessage(
                text =
                    "Hi! I'm your AI culinary assistant 🍳\n" +
                        "Ask me anything about recipes, cooking techniques, ingredients, or meal ideas!",
                isFromUser = false,
            ),
        ),
    val isLoading: Boolean = false,
    val errorMessage: String? = null,
)
