package com.example.kmptranslator.translate.data.remote

import com.example.kmptranslator.translate.domain.translate.ErrorType
import com.example.kmptranslator.translate.domain.translate.AppException
import io.ktor.client.statement.HttpResponse
import io.ktor.utils.io.errors.IOException

suspend inline fun <RESULT> safeApiCall(
    crossinline apiCall: suspend () -> HttpResponse,
    crossinline getResult: suspend (HttpResponse) -> RESULT
): RESULT {
    val result = try {
        apiCall()
    } catch (e: IOException) {
        throw AppException(ErrorType.SERVICE_UNAVAILABLE)
    }
    result.status.value.validateErrorCode()
    return try {
        getResult(result)
    } catch (e: Exception) {
        throw AppException(ErrorType.SERVER_ERROR)
    }
}