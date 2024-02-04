import org.gradle.api.artifacts.dsl.DependencyHandler

object SqlDelight {
    private const val sqlDelightVersion = "1.5.4"
    const val sqlDelightRuntime = "com.squareup.sqldelight:runtime:$sqlDelightVersion"
    const val sqlDelightAndroidDriver = "com.squareup.sqldelight:android-driver:$sqlDelightVersion"
    const val sqlDelightNativeDriver = "com.squareup.sqldelight:native-driver:$sqlDelightVersion"
    const val sqlDelightCoroutinesExtensions = "com.squareup.sqldelight:coroutines-extensions:$sqlDelightVersion"
}

fun DependencyHandler.sqlDelightCommonMain() {
    implementation(SqlDelight.sqlDelightRuntime)
    implementation(SqlDelight.sqlDelightCoroutinesExtensions)
}

fun DependencyHandler.sqlDelightAndroid() {
    implementation(SqlDelight.sqlDelightAndroidDriver)
}

fun DependencyHandler.sqlDelightIos() {
    implementation(SqlDelight.sqlDelightNativeDriver)
}