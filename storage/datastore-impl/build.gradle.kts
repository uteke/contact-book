plugins {
    alias(libs.plugins.androidLibraryConventions)
    alias(libs.plugins.testConventions)
    alias(libs.plugins.protobuf)
}

android {
    namespace = "${AppConfig.namespace}.storage.user"
}

protobuf {
    protoc {
        artifact = libs.protobuf.protoc.get().toString()
    }

    generateProtoTasks {
        all().forEach { task ->
            task.builtins {
                create("kotlin") {
                    option("lite")
                }
            }
            task.builtins {
                create("java") {
                    option("lite")
                }
            }
        }
    }
}

dependencies {
    implementation(projects.storage.userApi)
    implementation(libs.androidx.datastore)
    implementation(libs.koin)
    implementation(libs.protobuf.javaLite)
    implementation(libs.protobuf.kotlinLite)
}
