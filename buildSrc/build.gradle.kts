plugins {
    `kotlin-dsl`
}

gradlePlugin {
    plugins {
        register("android-module") {
            id = "android-module"
            implementationClass = "AndroidModulePlugin"
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