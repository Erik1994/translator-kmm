import org.gradle.api.artifacts.dsl.DependencyHandler

object KotlinDateTime {
    private const val dateTimeVersion = "0.4.0"
    const val kotlinDateTime = "org.jetbrains.kotlinx:kotlinx-datetime:$dateTimeVersion"
}

fun DependencyHandler.kotlinDateTimeCommonMain() {
    implementation(KotlinDateTime.kotlinDateTime)
}