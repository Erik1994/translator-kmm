package com.example.kmptranslator.translate.prensentation

import com.example.kmptranslator.core.presentation.UiLanguage
import com.example.kmptranslator.translate.domain.translate.ErrorType

data class TranslateState (
    val fromText: String = "",
    val toText: String? = null,
    val isTranslating: Boolean = false,
    val fromLanguage: UiLanguage = UiLanguage.byCode("en"),
    val toLanguage: UiLanguage = UiLanguage.byCode("ru"),
    val isChoosingFromLanguage: Boolean = false,
    val isChoosingToLanguage: Boolean = false,
    val error: ErrorType? = null,
    val histories: List<UiHistoryItem> = emptyList()
)
