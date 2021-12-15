package com.example.composetraining.core.data.repository

import com.example.composetraining.core.data.model.MemeModel

class MemeRepository {

    companion object {

        fun getMemes(): List<MemeModel> {
            return generateSequence(0) { it + 1 }
                .take(50)
                .map { MemeModel(it.toString()) }
                .toList()
        }
    }
}