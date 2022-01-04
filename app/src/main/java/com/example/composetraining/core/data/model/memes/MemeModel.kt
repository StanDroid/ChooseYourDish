package com.example.composetraining.core.data.model.memes

data class MemeModel(
    val name: String,
    val topText: String = "",
    val image: String,
    var state: Boolean = false)