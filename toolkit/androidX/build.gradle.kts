plugins {
    alias(libs.plugins.toolkit.androidX.module)
}

android {
    namespace = "com.vald3nir.toolkit.androidx"
}

dependencies {
    implementation(project(":toolkit:helpers"))
    // Image Loader
    api(libs.glide)
}