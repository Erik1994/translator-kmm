package com.example.kmptranslator.translate.domain.translate.usecase

import com.example.kmptranslator.core.domain.language.Language
import com.example.kmptranslator.core.domain.util.Resource
import com.example.kmptranslator.translate.domain.translate.AppException
import com.example.kmptranslator.translate.domain.translate.client.TranslateClient
import com.example.kmptranslator.translate.domain.translate.history.HistoryDataSource
import com.example.kmptranslator.translate.domain.translate.history.HistoryItem

class TranslateUseCaseImpl(
    private val translateClient: TranslateClient,
    private val historyDataSource: HistoryDataSource
) : TranslateUseCase {
    override suspend fun invoke(
        fromLanguage: Language,
        fromText: String,
        toLanguage: Language
    ): Resource<String> {
        return try {
            val translatedText = translateClient(
                text = fromText,
                fromLanguage = fromLanguage,
                toLanguage = toLanguage
            )

            historyDataSource.insertHistoryItem(
                HistoryItem(
                    id = null,
                    fromLanguageCode = fromLanguage.langCode,
                    fromText = fromText,
                    toLanguageCode = toLanguage.langCode,
                    toText = translatedText
                )
            )
            Resource.Success(translatedText)
        } catch (t: AppException) {
            t.printStackTrace()
            Resource.Error(t)
        }
    }
}