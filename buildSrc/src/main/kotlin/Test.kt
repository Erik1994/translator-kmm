import org.gradle.api.artifacts.dsl.DependencyHandler

object Test {
    private const val assertKVersion = "0.25"
    const val assertK = "com.willowtreeapps.assertk:assertk:$assertKVersion"

    private const val turbineVersion = "0.7.0"
    const val turbine = "app.cash.turbine:turbine:$turbineVersion"

    private const val jUnitVersion = "4.13.2"
    const val jUnit = "junit:junit:$jUnitVersion"

    private const val testRunnerVersion = "1.5.1"
    const val testRunner = "androidx.test:runner:$testRunnerVersion"

    const val composeTesting = "androidx.compose.ui:ui-test-junit4:${Compose.composeVersion}"
    const val composeTestManifest = "androidx.compose.ui:ui-test-manifest:${Compose.composeVersion}"

    const val hiltTesting = "com.google.dagger:hilt-android-testing:${Hilt.hiltVersion}"
}

fun DependencyHandler.test() {
    androidTestImplementation(Test.testRunner)
    androidTestImplementation(Test.jUnit)
    androidTestImplementation(Test.composeTesting)
    debugImplementation(Test.composeTestManifest)

    kaptAndroidTest(Hilt.hiltAndroidCompiler)
    androidTestImplementation(Test.hiltTesting)
}

fun DependencyHandler.testCommonMain() {
    implementation(Test.assertK)
    implementation(Test.turbine)
}