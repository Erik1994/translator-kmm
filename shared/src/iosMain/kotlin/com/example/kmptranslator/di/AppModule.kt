package com.example.kmptranslator.di

import com.example.kmptranslator.TranslateDatabase
import com.example.kmptranslator.translate.data.history.SqlDelightHistoryDataSource
import com.example.kmptranslator.translate.data.local.DatabaseDriverFactory
import com.example.kmptranslator.translate.data.mapper.HISTORY_ENTITIES_TO_ITEMS_MAPPER
import com.example.kmptranslator.translate.data.remote.HttpClientFactory
import com.example.kmptranslator.translate.data.translate.clientimpl.KtorTranslateClient
import com.example.kmptranslator.translate.domain.translate.client.TranslateClient
import com.example.kmptranslator.translate.domain.translate.history.HistoryDataSource
import com.example.kmptranslator.translate.domain.translate.mapper.HISTORY_ITEMS_TO_UI_MAPPER
import com.example.kmptranslator.translate.domain.translate.usecase.GetUiHistoryItemsUseCase
import com.example.kmptranslator.translate.domain.translate.usecase.GetUiHistoryItemsUseCaseImpl
import com.example.kmptranslator.translate.domain.translate.usecase.TranslateUseCase
import com.example.kmptranslator.translate.domain.translate.usecase.TranslateUseCaseImpl

class AppModule {
    val historyDataSource: HistoryDataSource by lazy {
        SqlDelightHistoryDataSource(
            db = TranslateDatabase(DatabaseDriverFactory().create()),
            mapper = HISTORY_ENTITIES_TO_ITEMS_MAPPER
        )
    }

    private val translateClient: TranslateClient by lazy {
        KtorTranslateClient(httpClient = HttpClientFactory().create())
    }

    val translateUseCase: TranslateUseCase by lazy {
        TranslateUseCaseImpl(
            translateClient = translateClient,
            historyDataSource = historyDataSource
        )
    }

    val getUiHistoryItemsUseCase: GetUiHistoryItemsUseCase by lazy {
        GetUiHistoryItemsUseCaseImpl(
            historyDataSource = historyDataSource,
            mapper = HISTORY_ITEMS_TO_UI_MAPPER
        )
    }
}