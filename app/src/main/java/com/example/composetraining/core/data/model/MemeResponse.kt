package com.example.composetraining.core.data.model

data class MemeResponse(
    val code: Int?,
    val data: List<MemeModelDTO?>?,
    val message: String?,
    val next: String?
)

data class MemeModelDTO(
    val ID: Int?,
    val bottomText: String?,
    val image: String?,
    val name: String?,
    val rank: Int?,
    val tags: String?,
    val topText: String?
)