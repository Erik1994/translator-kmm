package com.example.kmptranslator.android.voicetotext.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.kmptranslator.voicetotext.domain.VoiceToTextParser
import com.example.kmptranslator.voicetotext.presentation.VoiceToTextEvent
import com.example.kmptranslator.voicetotext.presentation.VoiceToTextViewmodel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AndroidVoiceToTextParserViewModel @Inject constructor(
    private val parser: VoiceToTextParser
): ViewModel() {
    private val viewModel by lazy {
        VoiceToTextViewmodel(
            parser = parser,
            coroutineScope = viewModelScope
        )
    }

    val state = viewModel.state

    fun onEvent(event: VoiceToTextEvent) {
        viewModel.onEvent(event)
    }
}