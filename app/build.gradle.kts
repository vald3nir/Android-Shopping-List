import com.toolkit.plugs.getEnvParameter
import com.toolkit.plugs.setupSigningConfigs

plugins {
    alias(libs.plugins.toolkit.application)
    alias(libs.plugins.toolkit.hilt)
    alias(libs.plugins.toolkit.room)
    alias(libs.plugins.toolkit.firebase)
    alias(libs.plugins.serialization)
    alias(libs.plugins.google.gms.google.services)
}

// todo replace for your .env file path
val envFilePath = "D://Documents//GitHub//Projetos//Lista-de-compras//Backoffice-Shopping-List//shopping_list.env"
val pathKeyStore = getEnvParameter(envFilePath = envFilePath, key = "KEY_STORE_PATH")
val keyAlias = getEnvParameter(envFilePath = envFilePath, key = "KEY_STORE_ALIAS")
val keyPassword = getEnvParameter(envFilePath = envFilePath, key = "KEY_STORE_PASSWORD")
val storePassword = getEnvParameter(envFilePath = envFilePath, key = "STORE_PASSWORD")
val serverClientID = getEnvParameter(envFilePath = envFilePath, key = "SERVER_CLIENT_ID")

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
    implementation(project(":toolkit:compose"))
    implementation(project(":toolkit:helpers"))
    implementation(project(":toolkit:networking"))
    implementation(project(":toolkit:firebase"))
    implementation(project(":toolkit:autentication"))
}