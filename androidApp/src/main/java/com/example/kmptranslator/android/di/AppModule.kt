package com.example.kmptranslator.android.di

import android.app.Application
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
import com.squareup.sqldelight.db.SqlDriver
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.ktor.client.HttpClient
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Provides
    @Singleton
    fun provideHttpClient(): HttpClient {
        return HttpClientFactory().create()
    }

    @Provides
    @Singleton
    fun provideTranslateClient(
        httpClient: HttpClient
    ): TranslateClient {
        return KtorTranslateClient(httpClient)
    }

    @Provides
    @Singleton
    fun provideDatabaseDriver(app: Application): SqlDriver {
        return DatabaseDriverFactory(app).create()
    }

    @Provides
    @Singleton
    fun provideTranslateDb(sqlDriver: SqlDriver): TranslateDatabase {
        return TranslateDatabase(sqlDriver)
    }

    @Provides
    @Singleton
    fun provideHistoryDataSource(db: TranslateDatabase): HistoryDataSource {
        return SqlDelightHistoryDataSource(
            db = db,
            mapper = HISTORY_ENTITIES_TO_ITEMS_MAPPER
        )
    }

    @Provides
    @Singleton
    fun provideTranslateUseCase(
        client: TranslateClient,
        dataSource: HistoryDataSource
    ): TranslateUseCase {
        return TranslateUseCaseImpl(
            translateClient = client,
            historyDataSource = dataSource
        )
    }

    @Provides
    @Singleton
    fun provideGetHistoryItemUseCase(
        dataSource: HistoryDataSource
    ): GetUiHistoryItemsUseCase {
        return GetUiHistoryItemsUseCaseImpl(
            historyDataSource = dataSource,
            mapper = HISTORY_ITEMS_TO_UI_MAPPER
        )
    }

}