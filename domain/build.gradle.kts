plugins {
    id(Plugins.Java.TestFixtures)
    id(Plugins.Local.KotlinJvmLibraryModule)
}

dependencies {
    api(projects.baseTypes)

    testImplementation(libs.bundles.unitTest)

    testFixturesApi(testFixtures(projects.baseTypes))
}