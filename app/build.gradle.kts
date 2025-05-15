plugins {
    alias(libs.plugins.toolkit.application)
    alias(libs.plugins.toolkit.hilt)
    alias(libs.plugins.toolkit.room)
    alias(libs.plugins.toolkit.firebase)
    alias(libs.plugins.serialization)
    alias(libs.plugins.google.gms.google.services)
}

android {
    namespace = "com.vald3nir.shoppinglist"
    defaultConfig {
        applicationId = namespace
        versionCode = 1
        versionName = "1.0.0"

        buildConfigField("int", "DB_VERSION", versionCode.toString())
        buildConfigField("String", "SERVER_CLIENT_ID", "\"166751724594-12sob36ogkqtm1e00gk5mq9cttbd3in0.apps.googleusercontent.com\"")
    }
}

dependencies {
    implementation(project(":toolkit:compose"))
    implementation(project(":toolkit:helpers"))
    implementation(project(":toolkit:networking"))
    implementation(project(":toolkit:firebase"))
    implementation(project(":toolkit:autentication"))
}