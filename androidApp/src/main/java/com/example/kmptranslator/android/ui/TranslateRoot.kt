package com.example.kmptranslator.android.ui

import android.speech.tts.Voice
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.kmptranslator.android.core.presentation.Arguments
import com.example.kmptranslator.android.core.presentation.Routes
import com.example.kmptranslator.android.translate.presentation.AndroidTranslateViewModel
import com.example.kmptranslator.android.translate.presentation.TranslateScreen
import com.example.kmptranslator.android.voicetotext.presentation.AndroidVoiceToTextViewModel
import com.example.kmptranslator.android.voicetotext.presentation.VoiceToTextScreen
import com.example.kmptranslator.core.domain.util.orDefault
import com.example.kmptranslator.translate.prensentation.TranslateEvent
import com.example.kmptranslator.voicetotext.presentation.VoiceToTextEvent

@Composable
fun TranslateRoot() {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = Routes.TRANSLATE
    ) {
        composable(route = Routes.TRANSLATE) {
            val viewModel = hiltViewModel<AndroidTranslateViewModel>()
            val state by viewModel.state.collectAsState()
            val voiceResult by it
                .savedStateHandle.getStateFlow<String?>(Arguments.VOICE_RESULT_KEY, null)
                .collectAsState()

            LaunchedEffect(key1 = voiceResult) {
                viewModel.onEvent(TranslateEvent.SubmitVoiceResult(voiceResult))
                it.savedStateHandle[Arguments.VOICE_RESULT_KEY] = null
            }
            TranslateScreen(
                state = state,
                onEvent = { translateEvent ->
                    when (translateEvent) {
                        is TranslateEvent.RecordAudio -> navController.navigate(
                            route = Routes.VOICE_TO_TEXT + "/${state.fromLanguage.language.langCode}"
                        )

                        else -> viewModel.onEvent(translateEvent)
                    }
                }
            )
        }
        composable(route = Routes.VOICE_TO_TEXT + "/{${Arguments.LANGUAGE_CODE_KEY}}",
            arguments = listOf(
                navArgument(Arguments.LANGUAGE_CODE_KEY) {
                    type = NavType.StringType
                    defaultValue = Arguments.DEFAULT_LANGUAGE_CODE
                }
            )
        ) { navBackStackEntry ->
            val languageCode = navBackStackEntry.arguments?.getString(Arguments.LANGUAGE_CODE_KEY)
                .orDefault(Arguments.DEFAULT_LANGUAGE_CODE)
            val viewmodel = hiltViewModel<AndroidVoiceToTextViewModel>()
            val state by viewmodel.state.collectAsState()
            VoiceToTextScreen(
                state = state,
                languageCode = languageCode,
                onResult = {
                    navController.previousBackStackEntry?.savedStateHandle?.set(
                        key = Arguments.VOICE_RESULT_KEY,
                        it
                    )
                    navController.popBackStack()
                },
                onEvent = { event ->
                    when (event) {
                        is VoiceToTextEvent.Close -> navController.popBackStack()
                        else -> viewmodel.onEvent(event)
                    }
                }
            )
        }
    }
}