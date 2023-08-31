@file:Suppress("UnstableApiUsage")

import org.gradle.api.Project
import org.gradle.api.artifacts.MinimalExternalModuleDependency
import org.gradle.api.artifacts.VersionCatalog
import org.gradle.api.artifacts.VersionCatalogsExtension
import org.gradle.api.provider.Provider
import org.gradle.kotlin.dsl.getByType

internal val Project.libs: VersionCatalog
    get() = extensions.getByType<VersionCatalogsExtension>().named("libs")

internal val VersionCatalog.composeCompilerVersion: String
    get() = findVersionOrThrow("androidx-compose-compiler")

internal val VersionCatalog.desugar: Provider<MinimalExternalModuleDependency>
    get() = findLibraryOrThrow("desugar")
internal val VersionCatalog.composeBom: Provider<MinimalExternalModuleDependency>
    get() = findLibraryOrThrow("androidx-compose-bom")
internal val VersionCatalog.composeMaterial: Provider<MinimalExternalModuleDependency>
    get() = findLibraryOrThrow("androidx-compose-material3")
internal val VersionCatalog.composeUi: Provider<MinimalExternalModuleDependency>
    get() = findLibraryOrThrow("androidx-compose-ui")
internal val VersionCatalog.composeUiGraphics: Provider<MinimalExternalModuleDependency>
    get() = findLibraryOrThrow("androidx-compose-ui-graphics")
internal val VersionCatalog.composeUiToolingPreview: Provider<MinimalExternalModuleDependency>
    get() = findLibraryOrThrow("androidx-compose-ui-toolingPreview")
internal val VersionCatalog.composeUiTooling: Provider<MinimalExternalModuleDependency>
    get() = findLibraryOrThrow("androidx-compose-ui-tooling")
internal val VersionCatalog.composeCompiler: Provider<MinimalExternalModuleDependency>
    get() = findLibraryOrThrow("androidx-compose-compiler")
internal val VersionCatalog.testJunitApi: Provider<MinimalExternalModuleDependency>
    get() = findLibraryOrThrow("junit-api")
internal val VersionCatalog.testJunitEngine: Provider<MinimalExternalModuleDependency>
    get() = findLibraryOrThrow("junit-engine")
internal val VersionCatalog.testJunitParams: Provider<MinimalExternalModuleDependency>
    get() = findLibraryOrThrow("junit-params")
internal val VersionCatalog.testMockk: Provider<MinimalExternalModuleDependency>
    get() = findLibraryOrThrow("mockk")
internal val VersionCatalog.testCoroutines: Provider<MinimalExternalModuleDependency>
    get() = findLibraryOrThrow("coroutines-test")
internal val VersionCatalog.testTurbine: Provider<MinimalExternalModuleDependency>
    get() = findLibraryOrThrow("turbine")
internal val VersionCatalog.testKotest: Provider<MinimalExternalModuleDependency>
    get() = findLibraryOrThrow("kotest")

private fun VersionCatalog.findLibraryOrThrow(alias: String) =
    findLibrary(alias)
        .orElseThrow { NoSuchElementException("Library $alias not found in version catalog") }

private fun VersionCatalog.findVersionOrThrow(name: String) =
    findVersion(name)
        .orElseThrow { NoSuchElementException("Version $name not found in version catalog") }
        .requiredVersion
