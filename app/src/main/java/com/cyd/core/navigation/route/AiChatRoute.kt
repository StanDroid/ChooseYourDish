package com.cyd.core.navigation.route

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.cyd.feature.aichat.AiChatScreen
import com.cyd.feature.aichat.AiChatViewModel

@Composable
fun AiChatRoute() {
    val viewModel = hiltViewModel<AiChatViewModel>()
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    AiChatScreen(
        uiState = uiState,
        onSendMessage = viewModel::sendMessage,
    )
}