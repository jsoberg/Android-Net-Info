plugins {
    `kotlin-dsl`
}

dependencies {
    compileOnly(libs.android.gradlePlugin)
    compileOnly(libs.composeCompiler.gradlePlugin)
    compileOnly(libs.kotlin.gradlePlugin)
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