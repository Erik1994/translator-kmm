package com.example.kmptranslator.translate.domain.translate.client

import com.example.kmptranslator.core.domain.language.Language

interface TranslateClient {
    suspend operator fun invoke(
        text: String,
        toLanguage: Language,
        fromLanguage: Language,
    ): String
}