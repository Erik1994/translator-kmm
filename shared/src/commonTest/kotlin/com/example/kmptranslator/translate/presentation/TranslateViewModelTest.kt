package com.example.kmptranslator.translate.presentation

import app.cash.turbine.test
import com.example.kmptranslator.core.presentation.UiLanguage
import com.example.kmptranslator.translate.data.local.FakeHistoryDataSource
import com.example.kmptranslator.translate.data.remote.FakeTranslateClient
import com.example.kmptranslator.translate.domain.translate.AppException
import com.example.kmptranslator.translate.domain.translate.ErrorType
import com.example.kmptranslator.translate.domain.translate.history.HistoryItem
import com.example.kmptranslator.translate.domain.translate.mapper.HISTORY_ITEMS_TO_UI_MAPPER
import com.example.kmptranslator.translate.domain.translate.usecase.GetUiHistoryItemsUseCase
import com.example.kmptranslator.translate.domain.translate.usecase.GetUiHistoryItemsUseCaseImpl
import com.example.kmptranslator.translate.domain.translate.usecase.TranslateUseCaseImpl
import com.example.kmptranslator.translate.prensentation.TranslateEvent
import com.example.kmptranslator.translate.prensentation.TranslateState
import com.example.kmptranslator.translate.prensentation.TranslateViewModel
import com.example.kmptranslator.translate.prensentation.UiHistoryItem
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertTrue

class TranslateViewModelTest {

    private lateinit var viewModel: TranslateViewModel
    private lateinit var client: FakeTranslateClient
    private lateinit var dataSource: FakeHistoryDataSource
    private lateinit var getUiHistoryItemsUseCase: GetUiHistoryItemsUseCase

    @BeforeTest
    fun setup() {
        client = FakeTranslateClient()
        dataSource = FakeHistoryDataSource()
        getUiHistoryItemsUseCase = GetUiHistoryItemsUseCaseImpl(
            historyDataSource = dataSource,
            mapper = HISTORY_ITEMS_TO_UI_MAPPER
        )
        val translateUseCase = TranslateUseCaseImpl(
            translateClient = client,
            historyDataSource = dataSource
        )
        viewModel = TranslateViewModel(
            translateUseCase = translateUseCase,
            getUiHistoryItemsUseCase = getUiHistoryItemsUseCase,
            coroutineScope = CoroutineScope(Dispatchers.Default)
        )
    }

    @Test
    fun `State and history items are combined correctly`() = runBlocking {
        viewModel.state.test {
            val initialState = awaitItem()

            assertTrue {
                initialState == TranslateState()
            }

            val item = HistoryItem(
                id = 0,
                fromLanguageCode = "en",
                toLanguageCode = "fr",
                fromText = "Hello",
                toText = "Bonjour"
            )
            dataSource.insertHistoryItem(item)

            val state = awaitItem()

            val expectedItem = UiHistoryItem (
                id = item.id!!,
                fromLanguage = UiLanguage.byCode(item.fromLanguageCode),
                toLanguage = UiLanguage.byCode(item.toLanguageCode),
                fromText = item.fromText,
                toText = item.toText
            )

            assertTrue {
                state.histories.first() == expectedItem
            }
        }
    }

    @Test
    fun `Translate success - state updates correctly`() = runBlocking {
        viewModel.state.test {
            awaitItem()

            viewModel.onEvent(TranslateEvent.ChangeTranslationText(text = "Fake text"))
            awaitItem()

            viewModel.onEvent(TranslateEvent.Translate)

            val loadingState = awaitItem()

            assertTrue { loadingState.isTranslating }

            val resultState = awaitItem()

            assertTrue {
                resultState.isTranslating.not()
            }
            assertTrue {
                resultState.toText == client.translatedText
            }
        }
    }

    @Test
    fun `Translate failure - state updates correctly`() = runBlocking {
        client.appException = AppException(
            error = ErrorType.CLIENT_ERROR,
            errorMessage = "Error message"
        )
        viewModel.state.test {
            awaitItem()

            viewModel.onEvent(TranslateEvent.ChangeTranslationText(text = "Fake text"))
            awaitItem()

            viewModel.onEvent(TranslateEvent.Translate)
            awaitItem()

            val resultState = awaitItem()

            assertTrue {
                resultState.error != null
            }

            assertTrue {
                resultState.error == client.appException?.error
            }
        }
    }

}