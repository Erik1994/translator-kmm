package com.example.kmptranslator.core.domain.util

sealed class Resource<T>(val data: T?, val throwable: Throwable? = null) {
    class Success<T>(
        data: T,
    ) : Resource<T>(data)

    class Error<T>(
        throwable: Throwable
    ) : Resource<T>(data = null, throwable = throwable)
}


fun <T> T?.orDefault(value: T): T = this ?: value