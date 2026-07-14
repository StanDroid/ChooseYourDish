package com.cyd.data.aichat

import io.ktor.client.HttpClient
import io.ktor.client.engine.okhttp.OkHttp
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.serialization.gson.gson
import okhttp3.logging.HttpLoggingInterceptor
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class KtorAiChatNetwork @Inject constructor() {
    private val baseUrl = BuildConfig.AI_CHAT_BASE_URL.trim()
    val endpointPath: String = BuildConfig.AI_CHAT_ENDPOINT_PATH.trim().ifBlank { CHAT }
    val isConfigured: Boolean = baseUrl.isNotBlank()

    val httpClient by lazy {
        HttpClient(OkHttp) {
            expectSuccess = true
            engine {
                if (BuildConfig.DEBUG) {
                    addInterceptor(
                        HttpLoggingInterceptor().apply {
                            setLevel(HttpLoggingInterceptor.Level.BODY)
                        },
                    )
                }
            }
            defaultRequest {
                url(baseUrl)
            }
            install(ContentNegotiation) {
                gson()
            }
        }
    }

    companion object {
        private const val CHAT = "chat"
    }
}