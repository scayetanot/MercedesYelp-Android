package com.whatevrdev.data.network

sealed class ApiResult<out T> {
    data class OnSuccess<T>(val data: T) : ApiResult<T>()
    data class OnError(val exception: Exception) : ApiResult<Nothing>()
    object OnUnauthorized : ApiResult<Nothing>()
}