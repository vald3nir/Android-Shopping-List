plugins {
    alias(libs.plugins.toolkit.lib)
    alias(libs.plugins.toolkit.firebase)
}

android {
    namespace = "com.vald3nir.android.firebase"
}

dependencies {
    // Facebook SDK
    implementation("com.facebook.android:facebook-login:[13.0.0]")

}