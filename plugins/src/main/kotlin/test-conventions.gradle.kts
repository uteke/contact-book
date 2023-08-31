import org.gradle.kotlin.dsl.dependencies

dependencies {
    val testImplementation by configurations
    testImplementation(libs.testJunitApi)
    testImplementation(libs.testJunitEngine)
    testImplementation(libs.testJunitParams)
    testImplementation(libs.testMockk)
    testImplementation(libs.testCoroutines)
    testImplementation(libs.testTurbine)
    testImplementation(libs.testKotest)
}

tasks.withType<Test> {
    useJUnitPlatform()
}