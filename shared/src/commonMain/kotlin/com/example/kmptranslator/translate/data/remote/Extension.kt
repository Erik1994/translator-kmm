package com.example.kmptranslator.translate.data.remote

import com.example.kmptranslator.translate.domain.translate.ErrorType
import com.example.kmptranslator.translate.domain.translate.AppException

fun Int.validateErrorCode() {
    when (this) {
        in 200..299 -> Unit
        in 400..499 -> throw AppException(ErrorType.CLIENT_ERROR)
        500 -> throw AppException(ErrorType.SERVER_ERROR)
        else -> throw AppException(ErrorType.UNKNOWN_ERROR)
    }
}