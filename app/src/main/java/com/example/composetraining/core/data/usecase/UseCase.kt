package com.example.composetraining.core.data.usecase

interface UseCase<in P, out T> {

    fun execute(params: P): T
}

fun <T> UseCase<Nothing?, T>.execute() = execute(null)
