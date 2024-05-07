package com.example.kmptranslator.translate.data.local

import com.example.kmptranslator.core.domain.util.CommonFlow
import com.example.kmptranslator.core.domain.util.toCommonFlow
import com.example.kmptranslator.translate.domain.translate.history.HistoryDataSource
import com.example.kmptranslator.translate.domain.translate.history.HistoryItem
import kotlinx.coroutines.flow.MutableStateFlow

class FakeHistoryDataSource: HistoryDataSource {
    private val _items = MutableStateFlow<List<HistoryItem>>(emptyList())

    override fun getHistory(): CommonFlow<List<HistoryItem>> {
        return _items.toCommonFlow()
    }

    override suspend fun insertHistoryItem(item: HistoryItem) {
        _items.value += item
    }
}