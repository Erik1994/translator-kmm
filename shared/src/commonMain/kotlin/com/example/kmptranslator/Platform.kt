package com.example.kmptranslator

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform