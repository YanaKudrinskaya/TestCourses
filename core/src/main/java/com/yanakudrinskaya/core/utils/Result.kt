package com.yanakudrinskaya.core.utils

sealed class Result<out T> {
    data class Success<out T>(val data: T) : Result<T>()
    data class Error(val error: ResponseStatus, val message: String? = null) : Result<Nothing>()
}