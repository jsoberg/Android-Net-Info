import org.gradle.api.JavaVersion
import org.gradle.api.Plugin
import org.gradle.api.Project

class AndroidModulePlugin : Plugin<Project> {

    override fun apply(project: Project) {
        project.plugins.apply(Plugins.Kotlin.Android)
        configureAndroid(project)
    }

    private fun configureAndroid(project: Project) = with(project) {
        with(android) {
            compileOptions {
                sourceCompatibility = JavaVersion.VERSION_11
                targetCompatibility = JavaVersion.VERSION_11
            }

            kotlinOptions {
                jvmTarget = "11"
                allWarningsAsErrors = true
            }

            compileSdkVersion(Versions.Android.Sdk.compile)
            defaultConfig {
                minSdk = Versions.Android.Sdk.min
                targetSdk = Versions.Android.Sdk.target
            }
        }
    }


}