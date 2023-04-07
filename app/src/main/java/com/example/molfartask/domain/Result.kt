package com.example.molfartask.domain

sealed class Result<T>(
    val data: T? = null,
    val message: String? = null
) {
    class Success<T>(data: T) : Result<T>(data)
    class NoData<T>(message: String) : Result<T>(message = message)
    class Loading<T> : Result<T>()
}