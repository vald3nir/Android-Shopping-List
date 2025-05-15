package com.toolkit.plugs

import com.android.build.api.dsl.ApkSigningConfig
import com.android.build.api.dsl.ApplicationExtension
import org.gradle.api.Project
import org.gradle.api.artifacts.VersionCatalog
import org.gradle.api.artifacts.VersionCatalogsExtension
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.getByType
import java.io.File

val Project.libs // todo - valdenir remover referencia
    get(): VersionCatalog = extensions.getByType<VersionCatalogsExtension>().named("libs")

const val debugName = "debug"
const val releaseName = "release"


fun Project.setupBuildTypes() {
    extensions.configure<ApplicationExtension> {
        buildTypes.apply {
            maybeCreate(debugName)
            getByName(debugName) {
                applicationIdSuffix = ".${debugName}"
                isMinifyEnabled = false
                isDebuggable = true
            }
            maybeCreate(releaseName)
            getByName(releaseName) {
                isMinifyEnabled = true
                isDebuggable = false
                proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
            }
        }
    }
}

fun Project.setupSigningConfigs() {
    extensions.configure<ApplicationExtension> {
        val storeFilePath = rootProject.file("build-logic/convention/src/main/kotlin/com/toolkit/auth/keystore.jks")
        buildTypes.apply {
            signingConfigs {
                getByName(debugName) { inputParam(storeFilePath) }
                create(releaseName) { inputParam(storeFilePath) }
            }
        }
    }
}


private fun ApkSigningConfig.inputParam(storeFilePath: File) {
    keyAlias = "Auth"
    keyPassword = "x^~_AiVk_FZqKq)g"
    storeFile = storeFilePath
    storePassword = "x^~_AiVk_FZqKq)g"
}