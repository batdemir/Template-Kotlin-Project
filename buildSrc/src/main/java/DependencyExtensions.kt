import models.Dependency
import models.ExcludeWithDependency
import org.gradle.api.artifacts.ExternalModuleDependency
import org.gradle.api.artifacts.dsl.DependencyHandler
import org.gradle.kotlin.dsl.exclude

private fun addAny(
    handler: DependencyHandler,
    configurationName: String,
    dependencies: List<Dependency>
) {
    dependencies.forEach {
        val dependency = handler.add(configurationName, it.path)
        when (it) {
            is ExcludeWithDependency -> {
                if (dependency is ExternalModuleDependency && it.excludeDependency.isNotEmpty()) {
                    it.excludeDependency.forEach { excludeDependency ->
                        dependency.exclude(excludeDependency.group, excludeDependency.module)
                    }
                }
            }
        }
    }
}

fun DependencyHandler.kapt(dependencies: List<Dependency>) {
    addAny(this, "kapt", dependencies)
}

fun DependencyHandler.kaptTest(dependencies: List<Dependency>) {
    addAny(this, "kaptTest", dependencies)
}

fun DependencyHandler.kaptAndroidTest(dependencies: List<Dependency>) {
    addAny(this, "kaptAndroidTest", dependencies)
}

fun DependencyHandler.implementation(dependencies: List<Dependency>) {
    addAny(this, "implementation", dependencies)
}

fun DependencyHandler.testImplementation(dependencies: List<Dependency>) {
    addAny(this, "testImplementation", dependencies)
}

fun DependencyHandler.androidTestImplementation(dependencies: List<Dependency>) {
    addAny(this, "androidTestImplementation", dependencies)
}

fun DependencyHandler.debugImplementation(dependencies: List<Dependency>) {
    addAny(this, "debugImplementation", dependencies)
}

fun DependencyHandler.releaseImplementation(dependencies: List<Dependency>) {
    addAny(this, "releaseImplementation", dependencies)
}
