plugins {
    alias(libs.plugins.androidLibraryConventions)
    alias(libs.plugins.testConventions)
}

android {
    namespace = "${AppConfig.namespace}.features.userdetails"
}

dependencies {
    implementation(libs.androidx.compose.material)
    implementation(libs.coil.compose)
    implementation(libs.koin.compose)

    implementation(projects.features.common)
    implementation(projects.storage.userApi)
}