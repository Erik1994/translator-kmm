package com.example.kmptranslator.translate.data.local

import android.content.Context
import com.example.kmptranslator.TranslateDatabase
import com.example.kmptranslator.translate.data.local.constants.Db
import com.squareup.sqldelight.android.AndroidSqliteDriver
import com.squareup.sqldelight.db.SqlDriver

@Suppress("EXPECT_ACTUAL_CLASSIFIERS_ARE_IN_BETA_WARNING")
actual class DatabaseDriverFactory (
    private val context:Context
){
    actual fun create(): SqlDriver {
       return AndroidSqliteDriver(
            TranslateDatabase.Schema,
            context,
            Db.name
        )
    }
}