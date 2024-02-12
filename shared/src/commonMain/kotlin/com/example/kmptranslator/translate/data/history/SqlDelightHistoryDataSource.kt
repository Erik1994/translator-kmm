package com.example.kmptranslator.translate.data.history

import com.example.kmptranslator.TranslateDatabase
import com.example.kmptranslator.core.data.mapper.Mapper
import com.example.kmptranslator.core.domain.util.CommonFlow
import com.example.kmptranslator.core.domain.util.toCommonFlow
import com.example.kmptranslator.translate.domain.translate.history.HistoryDataSource
import com.example.kmptranslator.translate.domain.translate.history.HistoryItem
import com.squareup.sqldelight.runtime.coroutines.asFlow
import com.squareup.sqldelight.runtime.coroutines.mapToList
import database.HistoryEntity
import kotlinx.coroutines.flow.map
import kotlinx.datetime.Clock

class SqlDelightHistoryDataSource(
    private val db: TranslateDatabase,
    private val mapper: Mapper<List<HistoryEntity>, List<HistoryItem>>
) : HistoryDataSource {

    private val queries = db.translateQueries

    override fun getHistory(): CommonFlow<List<HistoryItem>> {
        return queries.getHistory()
            .asFlow()
            .mapToList()
            .map { historyEntities ->
                mapper.map(historyEntities)
            }.toCommonFlow()
    }

    override suspend fun insertHistoryItem(item: HistoryItem) {
        queries.insertHistoryEntity(
            id = item.id,
            fromLanguageCode = item.fromLanguageCode,
            fromText = item.fromText,
            toLanguageCode = item.toLanguageCode,
            toText = item.toText,
            timestamp = Clock.System.now().toEpochMilliseconds()
        )
    }
}