plugins {
    alias(libs.plugins.androidLibraryConventions)
}

android {
    namespace = "${AppConfig.namespace}.features.common"
}

dependencies {
    implementation(libs.androidx.compose.material)
    implementation(libs.coroutines.test)
    implementation(libs.koin)
}