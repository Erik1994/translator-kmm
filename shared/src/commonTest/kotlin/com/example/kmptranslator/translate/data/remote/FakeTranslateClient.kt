package com.example.kmptranslator.translate.data.remote

import com.example.kmptranslator.core.domain.language.Language
import com.example.kmptranslator.translate.domain.translate.AppException
import com.example.kmptranslator.translate.domain.translate.client.TranslateClient

class FakeTranslateClient: TranslateClient {
    var translatedText = "test translation"
    var appException : AppException? = null
    override suspend fun invoke(
        text: String,
        toLanguage: Language,
        fromLanguage: Language
    ): String {
        appException?.let {
            throw it
        }
       return translatedText
    }
}