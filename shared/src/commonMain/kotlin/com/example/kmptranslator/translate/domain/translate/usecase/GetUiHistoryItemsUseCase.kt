package com.example.kmptranslator.translate.domain.translate.usecase

import com.example.kmptranslator.translate.prensentation.UiHistoryItem
import kotlinx.coroutines.flow.Flow

interface GetUiHistoryItemsUseCase {
    operator fun invoke(): Flow<List<UiHistoryItem>>
}