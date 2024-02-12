package com.example.kmptranslator.translate.domain.translate

enum class ErrorType {
    SERVICE_UNAVAILABLE,
    CLIENT_ERROR,
    SERVER_ERROR,
    UNKNOWN_ERROR
}

class AppException(
    val error: ErrorType,
    val errorMessage: String = "An error occurred when translating"
) : Exception(
    "$errorMessage: $error"
)