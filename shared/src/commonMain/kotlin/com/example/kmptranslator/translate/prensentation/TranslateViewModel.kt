package com.example.kmptranslator.translate.prensentation

import com.example.kmptranslator.core.domain.util.Resource
import com.example.kmptranslator.core.domain.util.orDefault
import com.example.kmptranslator.core.domain.util.toCommonStateFlow
import com.example.kmptranslator.translate.domain.translate.AppException
import com.example.kmptranslator.translate.domain.translate.usecase.TranslateUseCase
import com.example.kmptranslator.translate.domain.translate.history.HistoryDataSource
import com.example.kmptranslator.translate.domain.translate.usecase.GetUiHistoryItemsUseCase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.cancelChildren
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.flow.updateAndGet
import kotlinx.coroutines.launch

class TranslateViewModel(
    private val translateUseCase: TranslateUseCase,
    private val getUiHistoryItemsUseCase: GetUiHistoryItemsUseCase,
    private val coroutineScope: CoroutineScope?
) {
    private val viewModelScope = coroutineScope ?: CoroutineScope(Dispatchers.Main.immediate)
    private val _state = MutableStateFlow(TranslateState())

    val state = combine(
        _state,
        getUiHistoryItemsUseCase()
    ) { state, histories ->
        if (state.histories != histories) {
            state.copy(
                histories = histories
            )
        } else state
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(STOP_TIMEOUT_MILLIS), TranslateState())
        .toCommonStateFlow()

    private var translateJob: Job? = null

    fun onEvent(event: TranslateEvent) {
        when (event) {
            is TranslateEvent.ChangeTranslationText -> _state.update {
                it.copy(fromText = event.text)
            }

            is TranslateEvent.ChooseFromLanguage -> _state.update {
                it.copy(
                    isChoosingFromLanguage = false,
                    fromLanguage = event.language
                )
            }

            is TranslateEvent.ChooseToLanguage -> {
                //update method executes asynchronously so in translate
                //method we can have old state and for that reason we get new state
                //and pass as a parameter to translate method
                val newState = _state.updateAndGet {
                    it.copy(
                        isChoosingToLanguage = false,
                        toLanguage = event.language
                    )
                }
                translate(newState)
            }

            TranslateEvent.CloseTranslation -> _state.update {
                it.copy(
                    isTranslating = false,
                    fromText = "",
                    toText = null
                )
            }

            TranslateEvent.EditTranslation -> {
                state.value.toText?.let {
                    _state.update {
                        it.copy(
                            toText = null,
                            isTranslating = false
                        )
                    }
                }
            }

            TranslateEvent.OnErrorSeen -> _state.update {
                it.copy(error = null)
            }

            TranslateEvent.OpenFromLanguageDropDown -> _state.update {
                it.copy(isChoosingFromLanguage = true)
            }

            TranslateEvent.OpenToLanguageDropDown -> _state.update {
                it.copy(isChoosingToLanguage = true)
            }

            is TranslateEvent.SelectHistoryItem -> {
                translateJob?.cancel()
                _state.update {
                    it.copy(
                        fromText = event.item.fromText,
                        toText = event.item.toText,
                        isTranslating = false,
                        fromLanguage = event.item.fromLanguage,
                        toLanguage = event.item.toLanguage
                    )
                }
            }

            TranslateEvent.StopChoosingLanguage -> _state.update {
                it.copy(
                    isChoosingToLanguage = false,
                    isChoosingFromLanguage = false
                )
            }

            is TranslateEvent.SubmitVoiceResult -> _state.update {
                it.copy(
                    fromText = event.result.orDefault(it.fromText),
                    isTranslating = if (event.result != null) false else it.isTranslating,
                    toText = if (event.result != null) null else it.toText,
                )
            }

            TranslateEvent.SwapLanguage -> _state.update {
                it.copy(
                    fromLanguage = it.toLanguage,
                    toLanguage = it.fromLanguage,
                    fromText = it.toText.orDefault(""),
                    toText = if (it.toText != null) it.fromText else null
                )
            }

            TranslateEvent.Translate -> translate(state.value)
            else -> Unit
        }
    }

    private fun translate(state: TranslateState) {
        if (state.isTranslating || state.fromText.isBlank()) {
            return
        }
        translateJob = viewModelScope.launch {
            _state.update {
                it.copy(isTranslating = true)
            }
            val result = translateUseCase(
                fromLanguage = state.fromLanguage.language,
                fromText = state.fromText,
                toLanguage = state.toLanguage.language
            )
            when (result) {
                is Resource.Success -> {
                    _state.update {
                        it.copy(
                            isTranslating = false,
                            toText = result.data
                        )
                    }
                }

                is Resource.Error -> {
                    _state.update {
                        it.copy(
                            isTranslating = false,
                            error = (result.throwable as? AppException)?.error
                        )
                    }
                }
            }
        }
    }

    private companion object {
        const val STOP_TIMEOUT_MILLIS = 5000L
    }
}