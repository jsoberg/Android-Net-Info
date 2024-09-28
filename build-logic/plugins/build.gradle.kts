plugins {
    `kotlin-dsl`
}

dependencies {
    implementation(libs.android.gradlePlugin)
    implementation(libs.composeCompiler.gradlePlugin)
    implementation(libs.kotlin.gradlePlugin)

    // TODO: Fixes dependency conflict issues with Dagger/Hilt, re: https://github.com/google/dagger/issues/3068.
    implementation(libs.javapoet) {
        because(
            "Required for fixing dependency conflict with Dagger/Hilt 2.40.2 and above (see https://github.com/google/dagger/issues/3068)"
        )
    }

    // TODO: Fixes dependency analysis resolution conflict, re: https://github.com/autonomousapps/dependency-analysis-gradle-plugin/issues/1240
    implementation(libs.google.guava) {
        because(
            "Required for fixing dependency conflict with Dependency Analysis 1.33.0 and above (see https://github.com/autonomousapps/dependency-analysis-gradle-plugin/issues/1240)"
        )
    }
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