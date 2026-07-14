package com.cyd.domain.aichat

sealed class AiChatException(
    message: String,
) : Exception(message) {
    class BackendNotConfigured: AiChatException("AI chat backend is not configured.")
    class NetworkUnavailable : AiChatException("AI chat backend is unavailable.")
    class RequestRejected : AiChatException("AI chat request was rejected.")
    class ServerUnavailable : AiChatException("AI chat backend had a problem.")
    class EmptyResponse : AiChatException("AI chat backend returned an empty response.")
}