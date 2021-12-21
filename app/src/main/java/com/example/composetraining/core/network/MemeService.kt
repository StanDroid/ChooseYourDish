package com.example.composetraining.core.network

import com.example.composetraining.core.data.model.MemeResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path

interface MemeService {

    @GET("/{page}/")
    fun getMemes(@Path("page") page: Int): Single<MemeResponse>
}