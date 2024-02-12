package com.example.kmptranslator.translate.domain.translate.usecase

import com.example.kmptranslator.core.data.mapper.Mapper
import com.example.kmptranslator.translate.domain.translate.history.HistoryDataSource
import com.example.kmptranslator.translate.domain.translate.history.HistoryItem
import com.example.kmptranslator.translate.prensentation.UiHistoryItem
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.mapNotNull

class GetUiHistoryItemsUseCaseImpl(
    private val historyDataSource: HistoryDataSource,
    private val mapper: Mapper<List<HistoryItem>, List<UiHistoryItem>>
): GetUiHistoryItemsUseCase {
    override fun invoke(): Flow<List<UiHistoryItem>> = historyDataSource.getHistory()
        .mapNotNull {
            mapper.map(it)
        }
}