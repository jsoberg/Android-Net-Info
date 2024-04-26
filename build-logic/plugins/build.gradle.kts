plugins {
    `kotlin-dsl`
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

dependencies {
    // Give plugin code access to the libs version catalog.
    implementation(files(libs.javaClass.superclass.protectionDomain.codeSource.location))

    implementation(libs.android.gradlePlugin)
    implementation(libs.kotlin.gradlePlugin)

    // TODO: Fixes dependency conflict issues with Dagger/Hilt, re: https://github.com/google/dagger/issues/3068. Once this issues is resolved and we upgrade dagger/hilt, remove this dependency.
    implementation(libs.javapoet)
}