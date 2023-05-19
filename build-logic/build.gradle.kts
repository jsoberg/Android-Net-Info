plugins {
    `kotlin-dsl`
}

gradlePlugin {
    plugins {
        register("android.compose") {
            id = "android.compose"
            implementationClass = "com.soberg.netinfo.gradle.plugin.AndroidComposeConventionPlugin"
        }

        register("android.module") {
            id = "android.module"
            implementationClass = "com.soberg.netinfo.gradle.plugin.AndroidModulePlugin"
        }

        register("deps") {
            id = "deps"
            implementationClass = "com.soberg.netinfo.gradle.plugin.DependenciesPlugin"
        }

        register("kotlin.library.module") {
            id = "kotlin.library.module"
            implementationClass = "com.soberg.netinfo.gradle.plugin.KotlinJvmLibraryModulePlugin"
        }
    }
}

repositories {
    google()
    mavenCentral()
}

dependencies {
    implementation(libs.android.gradlePlugin)
    implementation(libs.kotlin.gradlePlugin)
}