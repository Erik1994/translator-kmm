package com.example.kmptranslator.translate.data.translate.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class TranslateDto(
    @SerialName("q") val text: String,
    @SerialName("source") val sourceLanguageCode: String,
    @SerialName("target") val targetLanguageCode: String
)
