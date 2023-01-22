plugins {
    `kotlin-dsl`
}

gradlePlugin {
    plugins {
        register("android-module") {
            id = "android-module"
            implementationClass = "AndroidModulePlugin"
        }

        register("kotlin-jvm-library-module") {
            id = "kotlin-jvm-library-module"
            implementationClass = "KotlinJvmLibraryModulePlugin"
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