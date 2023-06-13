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
    // Give plugin code access to the libs version catalog.
    compileOnly(files(libs.javaClass.superclass.protectionDomain.codeSource.location))

    implementation(libs.android.gradlePlugin)
    implementation(libs.kotlin.gradlePlugin)
}