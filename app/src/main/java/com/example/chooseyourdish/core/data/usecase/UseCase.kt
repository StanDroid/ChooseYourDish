package com.example.chooseyourdish.core.data.usecase

interface UseCaseSuspend<in P, out T> {

    suspend fun execute(params: P): T
}
suspend fun <T> UseCaseSuspend<Nothing?, T>.execute() = execute(null)
