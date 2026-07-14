package com.cyd.data.aichat

import com.cyd.data.aichat.model.AiChatRequest
import com.cyd.data.aichat.model.AiChatResponse
import com.cyd.domain.aichat.AiChatException
import io.ktor.client.call.body
import io.ktor.client.plugins.ClientRequestException
import io.ktor.client.plugins.ServerResponseException
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.http.ContentType
import io.ktor.http.contentType
import java.io.IOException
import java.net.ConnectException
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class KtorAiChatDataSourceImpl @Inject constructor(
    private val network: KtorAiChatNetwork,
    private val geminiAiChatClient: GeminiAiChatClient,
) : AiChatDataSource {
    override suspend fun sendMessage(message: String): Result<String> {
        if (!network.isConfigured) {
            return geminiAiChatClient.sendMessage(message)
        }

        return try {
            val response =
                network.httpClient
                    .post(network.endpointPath) {
                        contentType(ContentType.Application.Json)
                        setBody(AiChatRequest(message = message))
                    }.body<AiChatResponse>()

            val reply = response.reply.trim()
            if (reply.isBlank()) {
                Result.failure(AiChatException.EmptyResponse())
            } else {
                Result.success(reply)
            }
        } catch (e: ClientRequestException) {
            Result.failure(AiChatException.RequestRejected())
        } catch (e: ServerResponseException) {
            fallbackToLocalGemini(message, AiChatException.ServerUnavailable())
        } catch (e: ConnectException) {
            fallbackToLocalGemini(message, AiChatException.NetworkUnavailable())
        } catch (e: IOException) {
            fallbackToLocalGemini(message, AiChatException.NetworkUnavailable())
        }
    }

    private suspend fun fallbackToLocalGemini(
        message: String,
        fallbackError: AiChatException,
    ): Result<String> =
        if (geminiAiChatClient.isConfigured) {
            geminiAiChatClient.sendMessage(message)
        } else {
            Result.failure(fallbackError)
        }
}