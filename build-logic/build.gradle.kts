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

        register("local.root") {
            id = "local.root"
            implementationClass = "com.soberg.gradle.plugin.RootPlugin"
        }

        register("local.kotlin.library.module") {
            id = "local.kotlin.library.module"
            implementationClass = "com.soberg.gradle.plugin.KotlinJvmLibraryModulePlugin"
        }
    }
}

repositories {
    google()
    mavenCentral()
}

dependencies {
    // Give plugin code access to the libs version catalog.
    compileOnly(files(libs.javaClass.superclass.protectionDomain.codeSource.location))

    implementation(libs.android.gradlePlugin)
    implementation(libs.kotlin.gradlePlugin)
}