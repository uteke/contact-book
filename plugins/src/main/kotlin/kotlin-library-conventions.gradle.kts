plugins {
    `java-library`
    id("kotlin")
    id("kotlin-conventions")
}

task("testUnitTest") {
    dependsOn("test")
}