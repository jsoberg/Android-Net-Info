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
        register("local.android.compose") {
            id = "local.android.compose"
            implementationClass = "com.soberg.gradle.plugin.AndroidComposeConventionPlugin"
        }

        register("local.android.module") {
            id = "local.android.module"
            implementationClass = "com.soberg.gradle.plugin.AndroidModulePlugin"
        }

        register("local.kotlin.library.module") {
            id = "local.kotlin.library.module"
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