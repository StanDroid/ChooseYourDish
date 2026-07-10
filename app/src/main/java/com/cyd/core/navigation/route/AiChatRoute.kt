package com.cyd.core.navigation.route

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.cyd.BuildConfig
import com.cyd.feature.aichat.AiChatScreen
import com.cyd.feature.aichat.AiChatViewModel

@Composable
fun AiChatRoute() {
    val viewModel = hiltViewModel<AiChatViewModel>()
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    LaunchedEffect(Unit) {
        viewModel.initModel(BuildConfig.GEMINI_API_KEY)
    }

    AiChatScreen(
        uiState = uiState,
        onSendMessage = viewModel::sendMessage,
    )
}
