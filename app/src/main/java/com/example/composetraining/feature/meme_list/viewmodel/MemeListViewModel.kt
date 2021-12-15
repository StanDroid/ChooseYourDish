package com.example.composetraining.feature.meme_list.viewmodel

import com.example.composetraining.core.data.model.MemeModel
import com.example.composetraining.core.data.repository.MemeRepository

class MemeListViewModel {

    fun getMemeList(): List<MemeModel> {
        return MemeRepository.getMemes()
    }
}