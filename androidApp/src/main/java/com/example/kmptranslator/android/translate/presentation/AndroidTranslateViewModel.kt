package com.example.kmptranslator.android.translate.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.kmptranslator.translate.domain.translate.usecase.GetUiHistoryItemsUseCase
import com.example.kmptranslator.translate.domain.translate.usecase.TranslateUseCase
import com.example.kmptranslator.translate.prensentation.TranslateEvent
import com.example.kmptranslator.translate.prensentation.TranslateViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AndroidTranslateViewModel @Inject constructor(
    private val translateUseCase: TranslateUseCase,
    private val getUiHistoryItemsUseCase: GetUiHistoryItemsUseCase
) : ViewModel() {
    private val viewModel by lazy {
        TranslateViewModel(
            translateUseCase = translateUseCase,
            getUiHistoryItemsUseCase = getUiHistoryItemsUseCase,
            coroutineScope = viewModelScope
        )
    }
    val state = viewModel.state

    fun onEvent(event: TranslateEvent) {
        viewModel.onEvent(event)
    }

}