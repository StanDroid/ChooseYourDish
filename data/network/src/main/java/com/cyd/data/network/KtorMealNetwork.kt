package com.cyd.data.network

import io.ktor.client.HttpClient
import io.ktor.client.engine.okhttp.OkHttp
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.serialization.gson.gson
import okhttp3.logging.HttpLoggingInterceptor
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class KtorMealNetwork @Inject constructor() {

    // creates Ktor client with OkHttp engine
    val httpClient by lazy {
        HttpClient(OkHttp) {
            // default validation to throw exceptions for non-2xx responses
            expectSuccess = true
            engine {
                addInterceptor(HttpLoggingInterceptor().apply {
                    setLevel(
                        HttpLoggingInterceptor.Level.BODY
                    )
                })
            }
            // set default request parameters
            defaultRequest {
                url(BASE_URL)
            }
            // use gson content negotiation for serialize or deserialize
            install(ContentNegotiation) {
                gson()
            }
        }
    }
}

