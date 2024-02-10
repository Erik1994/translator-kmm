package com.example.kmptranslator.translate.data.mapper

import com.example.kmptranslator.core.data.mapper.Mapper
import com.example.kmptranslator.translate.domain.translate.history.HistoryItem
import database.HistoryEntity

val HISTORY_ENTITY_TO_ITEM_MAPPER = Mapper<HistoryEntity, HistoryItem> {
    it.run {
        HistoryItem(
            id = id,
            fromLanguageCode = fromLanguageCode,
            fromText = fromText,
            toLanguageCode = toLanguageCode,
            toText = toText
        )
    }
}

val HISTORY_ENTITIES_TO_ITEMS_MAPPER =
    Mapper<List<HistoryEntity>, List<HistoryItem>> { historyEntities ->
        historyEntities.map { HISTORY_ENTITY_TO_ITEM_MAPPER.map(it) }
    }