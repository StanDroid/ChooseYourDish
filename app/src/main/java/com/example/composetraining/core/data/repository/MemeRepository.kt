package com.example.composetraining.core.data.repository

import com.example.composetraining.core.data.model.MemeResponse
import io.reactivex.Single

interface MemeRepository {
    fun getMemes(page: Int = 1): Single<MemeResponse>
}