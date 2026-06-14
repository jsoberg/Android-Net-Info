plugins {
    `kotlin-dsl`
}

dependencies {
    implementation(libs.android.gradlePlugin)
    implementation(libs.composeCompiler.gradlePlugin)
    implementation(libs.kotlin.gradlePlugin)
}

gradlePlugin {
    plugins {
        register("local.android.app") {
            id = "local.android.app"
            implementationClass = "com.soberg.gradle.plugin.AndroidAppPlugin"
        }

        register("local.android.compose") {
            id = "local.android.compose"
            implementationClass = "com.soberg.gradle.plugin.AndroidComposePlugin"
        }

        register("local.android.library") {
            id = "local.android.library"
            implementationClass = "com.soberg.gradle.plugin.AndroidLibraryPlugin"
        }

        register("local.junit.testable") {
            id = "local.junit.testable"
            implementationClass = "com.soberg.gradle.plugin.JunitTestablePlugin"
        }

        register("local.kotlin.library") {
            id = "local.kotlin.library"
            implementationClass = "com.soberg.gradle.plugin.KotlinJvmLibraryModulePlugin"
        }

        register("local.root.project") {
            id = "local.root.project"
            implementationClass = "com.soberg.gradle.plugin.RootProjectPlugin"
        }

        register("local.root.settings") {
            id = "local.root.settings"
            implementationClass = "com.soberg.gradle.plugin.RootSettingsPlugin"
        }
    }
}