plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.androidLibrary)
    kotlin("plugin.serialization") version Gradle.kotlinVersion
    id("com.squareup.sqldelight")
}

kotlin {
    androidTarget {
        compilations.all {
            kotlinOptions {
                jvmTarget = "1.8"
            }
        }
    }
    
    listOf(
        iosX64(),
        iosArm64(),
        iosSimulatorArm64()
    ).forEach {
        it.binaries.framework {
            baseName = "shared"
            isStatic = true
        }
    }

    sourceSets {
        commonMain.dependencies {
            //put your multiplatform dependencies here
            implementation(Ktor.ktorCore)
            implementation(Ktor.ktorSerialization)
            implementation(Ktor.ktorSerializationJson)
            implementation(SqlDelight.sqlDelightRuntime)
            implementation(SqlDelight.sqlDelightCoroutinesExtensions)
            implementation(KotlinDateTime.kotlinDateTime)
        }
        commonTest.dependencies {
            implementation(libs.kotlin.test)
            implementation(Test.assertK)
            implementation(Test.turbine)
        }
        androidMain.dependencies {
            implementation(Ktor.ktorAndroid)
            implementation(SqlDelight.sqlDelightAndroidDriver)
        }
        iosMain.dependencies {
            implementation(Ktor.ktorIOS)
            implementation(SqlDelight.sqlDelightNativeDriver)
        }
    }
}

android {
    namespace = "com.example.kmptranslator"
    compileSdk = 34
    defaultConfig {
        minSdk = 24
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
}
