package com.example.kmptranslator.translate.domain.translate

enum class ErrorType {
    SERVICE_UNAVAILABLE,
    CLIENT_ERROR,
    SERVER_ERROR,
    UNKNOWN_ERROR
}

class AppException(val error: ErrorType) : Exception(
   "An error occurred when translating: $error"
)