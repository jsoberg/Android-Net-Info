buildscript {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }

    dependencies {
        classpath(libs.android.gradlePlugin)
        classpath(libs.kotlin.gradlePlugin)
        classpath(libs.hilt.agp)
    }
}

allprojects {
    tasks.withType<Test> {
        useJUnitPlatform()
    }
}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}