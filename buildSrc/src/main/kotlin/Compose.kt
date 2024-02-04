import org.gradle.api.artifacts.dsl.DependencyHandler

object Compose {
    private const val activityComposeVersion = "1.8.2"
    const val activityCompose = "androidx.activity:activity-compose:$activityComposeVersion"

    const val composeVersion = "1.6.0"
    const val composeUi = "androidx.compose.ui:ui:$composeVersion"
    const val composeUiTooling = "androidx.compose.ui:ui-tooling:$composeVersion"
    const val composeUiToolingPreview = "androidx.compose.ui:ui-tooling-preview:$composeVersion"
    const val composeFoundation = "androidx.compose.foundation:foundation:$composeVersion"
    const val composeMaterial = "androidx.compose.material:material:$composeVersion"
    private const val composeMaterialVersion = "1.1.2"
    const val material = "androidx.compose.material3:material3:$composeMaterialVersion"
    const val composeIconsExtended = "androidx.compose.material:material-icons-extended:$composeVersion"

    private const val composeNavigationVersion = "2.7.6"
    const val composeNavigation = "androidx.navigation:navigation-compose:$composeNavigationVersion"

    private const val coilComposeVersion = "2.5.0"
    const val coilCompose = "io.coil-kt:coil-compose:$coilComposeVersion"
}

fun DependencyHandler.compose() {
    implementation(Compose.composeUi)
    implementation(Compose.composeUiTooling)
    implementation(Compose.composeUiToolingPreview)
    implementation(Compose.composeFoundation)
    implementation(Compose.composeMaterial)
    implementation(Compose.activityCompose)
    implementation(Compose.material)
    implementation(Compose.composeIconsExtended)
    implementation(Compose.composeNavigation)
    implementation(Compose.coilCompose)
}