plugins {
    `kotlin-dsl`
}

dependencies {
    implementation(libs.android.gradlePlugin)
    implementation(libs.composeCompiler.gradlePlugin)
    implementation(libs.kotlin.gradlePlugin)
    
    // TODO: Fixes dependency conflict issues with Dagger/Hilt, re: https://github.com/google/dagger/issues/3068. Once this issues is resolved and we upgrade dagger/hilt, remove this dependency.
    implementation(libs.javapoet)
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