package com.whatevrdev.domain.entities

sealed class AppResult<out T> {
    data class OnSuccess<T>(val data: T) : AppResult<T>()
    data class OnError(val exception: Exception) : AppResult<Nothing>()
}