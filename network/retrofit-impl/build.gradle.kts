plugins {
    alias(libs.plugins.androidLibraryConventions)
    alias(libs.plugins.testConventions)
    alias(libs.plugins.kotlinKsp)
}

android {
    namespace = "${AppConfig.namespace}.network.retrofit"

    buildFeatures.buildConfig = true

    buildTypes {
        val baseUrl = project.properties["baseUrl"].toString()
        forEach {
            it.buildConfigField("String", "BASE_URL", baseUrl)
        }
    }
}

dependencies {
    implementation(libs.koin)
    implementation(libs.square.moshi.core)
    implementation(libs.square.okHttp.core)
    implementation(libs.square.okHttp.logging)
    implementation(libs.square.retrofit.core)
    implementation(libs.square.retrofit.moshi)
    ksp(libs.square.moshi.codegen)

    implementation(projects.network.userApi)

    testImplementation(libs.square.okHttp.mockwebserver)
}
