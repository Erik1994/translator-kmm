package com.example.kmptranslator.core.domain.usecase

import com.example.kmptranslator.core.domain.language.Language
import com.example.kmptranslator.core.domain.util.Resource

interface TranslateUseCase {
    suspend operator fun invoke(
        fromLanguage: Language,
        fromText: String,
        toLanguage: Language
    ): Resource<String>
}