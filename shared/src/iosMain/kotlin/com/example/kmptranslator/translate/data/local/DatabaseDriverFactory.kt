package com.example.kmptranslator.translate.data.local

import com.example.kmptranslator.TranslateDatabase
import com.example.kmptranslator.translate.data.local.constants.Db
import com.squareup.sqldelight.db.SqlDriver
import com.squareup.sqldelight.drivers.native.NativeSqliteDriver

@Suppress("EXPECT_ACTUAL_CLASSIFIERS_ARE_IN_BETA_WARNING")
actual class DatabaseDriverFactory {
    actual fun create(): SqlDriver {
       return NativeSqliteDriver(
            TranslateDatabase.Schema,
            Db.name
        )
    }
}