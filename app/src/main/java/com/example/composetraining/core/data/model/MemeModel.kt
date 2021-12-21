package com.example.composetraining.core.data.model

data class MemeModel(
    val name: String,
    val topText: String = "",
    var state: Boolean = false)