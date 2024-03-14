package com.example.kmptranslator.android.voicetotext.di

import android.app.Application
import com.example.kmptranslator.android.voicetotext.data.AndroidVoiceToTextParser
import com.example.kmptranslator.voicetotext.domain.VoiceToTextParser
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped


@Module
@InstallIn(ViewModelComponent::class)
object VoiceToTextModule {

    @Provides
    @ViewModelScoped
    fun provideVoiceToTextParser(app: Application): VoiceToTextParser {
        return AndroidVoiceToTextParser(app)
    }
}