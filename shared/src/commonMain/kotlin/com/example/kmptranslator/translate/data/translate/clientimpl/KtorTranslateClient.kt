package com.example.kmptranslator.translate.data.translate.clientimpl

import com.example.kmptranslator.translate.data.remote.Endpoint
import com.example.kmptranslator.translate.data.remote.safeApiCall
import com.example.kmptranslator.core.domain.language.Language
import com.example.kmptranslator.translate.data.translate.dto.TranslateDto
import com.example.kmptranslator.translate.data.translate.dto.TranslatedDto
import com.example.kmptranslator.translate.domain.translate.client.TranslateClient
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.client.request.url
import io.ktor.http.ContentType
import io.ktor.http.contentType

class KtorTranslateClient(
    private val httpClient: HttpClient
) : TranslateClient {
    override suspend fun invoke(
        text: String,
        toLanguage: Language,
        fromLanguage: Language
    ): String = safeApiCall(
        apiCall = {
            httpClient.post {
                url(Endpoint.Translate.url)
                contentType(ContentType.Application.Json)
                setBody(
                    TranslateDto(
                        text = text,
                        sourceLanguageCode = fromLanguage.langCode,
                        targetLanguageCode = toLanguage.langCode
                    )
                )
            }
        },
        getResult = {
            it.body<TranslatedDto>().translatedText
        }
    )
}