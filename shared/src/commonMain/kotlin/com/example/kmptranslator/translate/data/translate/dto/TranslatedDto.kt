package com.example.kmptranslator.translate.data.translate.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class TranslatedDto(
    @SerialName("translatedText") val translatedText: String
)
