package com.example.kmptranslator.translate.data.remote

sealed class Endpoint(val url: String) {

    data object Translate: Endpoint("$BASE_URL/translate")

    private companion object {
        const val BASE_URL = "https://translate.pl-coding.com"
    }
}