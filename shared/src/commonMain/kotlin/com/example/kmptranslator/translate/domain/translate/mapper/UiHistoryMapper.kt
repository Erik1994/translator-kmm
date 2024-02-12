package com.example.kmptranslator.translate.domain.translate.mapper

import com.example.kmptranslator.core.data.mapper.Mapper
import com.example.kmptranslator.core.domain.util.orDefault
import com.example.kmptranslator.core.presentation.UiLanguage
import com.example.kmptranslator.translate.domain.translate.history.HistoryItem
import com.example.kmptranslator.translate.prensentation.UiHistoryItem

val HISTORY_ITEM_TO_UI_MAPPER = Mapper<HistoryItem, UiHistoryItem> {
    it.run {
        UiHistoryItem(
            id = id.orDefault(0L),
            fromText = fromText,
            toText = toText,
            fromLanguage = UiLanguage.byCode(fromLanguageCode),
            toLanguage = UiLanguage.byCode(toLanguageCode)
        )
    }
}
val HISTORY_ITEMS_TO_UI_MAPPER = Mapper<List<HistoryItem>, List<UiHistoryItem>> { historyItems ->
    historyItems.map { HISTORY_ITEM_TO_UI_MAPPER.map(it) }
}