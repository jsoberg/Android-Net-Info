import org.gradle.api.Plugin
import org.gradle.api.Project

class KotlinJvmLibraryModulePlugin : Plugin<Project> {

    override fun apply(project: Project) {
        with(project.plugins) {
            apply(Plugins.Kotlin.Jvm)
            apply(Plugins.Java.Library)
        }
    }
}