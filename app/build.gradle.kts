import com.google.firebase.appdistribution.gradle.firebaseAppDistribution
import com.toolkit.plugs.getEnvParameter
import com.toolkit.plugs.setupSigningConfigs
import java.io.ByteArrayOutputStream

plugins {
    alias(libs.plugins.toolkit.application)
    alias(libs.plugins.toolkit.hilt)
    alias(libs.plugins.toolkit.room)
    alias(libs.plugins.toolkit.firebase)
    alias(libs.plugins.serialization)
    alias(libs.plugins.google.gms.google.services)
    alias(libs.plugins.google.firebase.appdistribution)
    alias(libs.plugins.google.firebase.crashlytics)
}

// todo replace for your .env file path
val envFilePath = "D://Documents//GitHub//Projetos//Lista-de-compras//Backoffice-Shopping-List//shopping_list.env"
val pathKeyStore = getEnvParameter(envFilePath = envFilePath, key = "KEY_STORE_PATH")
val keyAlias = getEnvParameter(envFilePath = envFilePath, key = "KEY_STORE_ALIAS")
val keyPassword = getEnvParameter(envFilePath = envFilePath, key = "KEY_STORE_PASSWORD")
val storePassword = getEnvParameter(envFilePath = envFilePath, key = "STORE_PASSWORD")
val serverClientID = getEnvParameter(envFilePath = envFilePath, key = "SERVER_CLIENT_ID")
val firebaseServiceAccountPath: String = "${project.projectDir}${File.separator}firebase-service-account.json"

firebaseAppDistribution {
    releaseNotes = getLastGitCommits(1)
    groups = "grupo-de-teste"
    serviceCredentialsFile = firebaseServiceAccountPath
}

fun getLastGitCommits(count: Int = 5): String {
    val output = ByteArrayOutputStream()
    exec {
        commandLine = listOf("git", "log", "-n", count.toString(), "--pretty=format:%h - %s (%an)")
        standardOutput = output
    }
    return output.toString().trim()
}

android {
    namespace = "com.vald3nir.shoppinglist"
    defaultConfig {
        applicationId = namespace
        versionCode = 1
        versionName = "1.0.0"

        setupSigningConfigs(
            pathKeyStore = pathKeyStore,
            keyAlias = keyAlias,
            keyPassword = keyPassword,
            storePassword = storePassword
        )

        buildConfigField("String", "SERVER_CLIENT_ID", serverClientID)
        buildConfigField("int", "DB_VERSION", versionCode.toString())
    }
}

dependencies {
    implementation(libs.firebase.crashlytics)
    implementation(project(":toolkit:compose"))
    implementation(project(":toolkit:helpers"))
    implementation(project(":toolkit:networking"))
    implementation(project(":toolkit:firebase"))
    implementation(project(":toolkit:autentication"))
}

tasks.register("uploadToFirebaseDistribution") {
    group = "app-distribution"
    description = "Builds release APK/AAB and uploads to Firebase App Distribution"
    dependsOn("assembleProdRelease", "appDistributionUploadProdRelease")
    doFirst {
        println("Iniciando upload para Firebase App Distribution...")
        println(firebaseServiceAccountPath)
    }
    doLast {
        println("Build concluído, iniciando upload para Firebase App Distribution...")
    }
}