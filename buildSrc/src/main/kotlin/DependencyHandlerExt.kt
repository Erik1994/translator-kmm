import org.gradle.api.artifacts.dsl.DependencyHandler
import org.gradle.kotlin.dsl.project

fun DependencyHandler.implementation(dependency: String) {
    add("implementation",  dependency)
}

fun DependencyHandler.testImplementation(dependency: String) {
    add("testImplementation",  dependency)
}

fun DependencyHandler.debugImplementation(dependency: String) {
    add("debugImplementation",  dependency)
}

fun DependencyHandler.androidTestImplementation(dependency: String) {
    add("androidTestImplementation",  dependency)
}

fun DependencyHandler.kaptAndroidTest(dependency: String) {
    add("kaptAndroidTest",  dependency)
}

fun DependencyHandler.kapt(dependency: String) {
    add("kapt",  dependency)
}

fun DependencyHandler.platformImpl(platformDependecy: String) {
    add("implementation", platform(platformDependecy))
}

fun DependencyHandler.projectImpl(path: String) {
    add("implementation", project(path))
}