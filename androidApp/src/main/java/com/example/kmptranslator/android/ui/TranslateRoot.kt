package com.example.kmptranslator.android.ui

import androidx.compose.runtime.Composable
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
import com.example.kmptranslator.translate.prensentation.TranslateEvent

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
            TranslateScreen(
                state = state,
                onEvent = {
                    when(it) {
                        is TranslateEvent.RecordAudio -> navController.navigate(
                            route = Routes.VOICE_TO_TEXT + "/${state.fromLanguage.language.langCode}"
                        )
                        else -> viewModel.onEvent(it)
                    }
                }
            )
        }
        composable(route = Routes.VOICE_TO_TEXT + "/{${Arguments.LANGUAGE_CODE}}",
            arguments = listOf(
                navArgument(Arguments.LANGUAGE_CODE) {
                    type = NavType.StringType
                    defaultValue = "en"
                }
            )
        ) {

        }
    }
}