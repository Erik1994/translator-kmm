import org.gradle.api.artifacts.dsl.DependencyHandler

object Ktor {
    private const val ktorVersion = "2.1.3"
    const val ktorCore = "io.ktor:ktor-client-core:$ktorVersion"
    const val ktorSerialization = "io.ktor:ktor-client-content-negotiation:$ktorVersion"
    const val ktorSerializationJson = "io.ktor:ktor-serialization-kotlinx-json:$ktorVersion"
    const val ktorAndroid = "io.ktor:ktor-client-android:$ktorVersion"
    const val ktorIOS = "io.ktor:ktor-client-ios:$ktorVersion"
}

fun DependencyHandler.ktorAndroid() {
    implementation(Ktor.ktorAndroid)
}

fun DependencyHandler.ktorCommonMain() {
    implementation(Ktor.ktorCore)
    implementation(Ktor.ktorSerialization)
    implementation(Ktor.ktorSerializationJson)
}

fun DependencyHandler.ktorIos() {
    implementation(Ktor.ktorIOS)
}