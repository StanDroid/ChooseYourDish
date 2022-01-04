package com.example.composetraining.core.data.repository

import com.example.composetraining.core.data.model.MemeResponse
import com.example.composetraining.core.network.memes.MemeService
import io.reactivex.Single
import javax.inject.Inject

class MemeRepositoryImpl @Inject constructor(
    private val memeService: MemeService
) : MemeRepository {

    override fun getMemes(page: Int): Single<MemeResponse> {
        return memeService.getMemes(page)
    }

}