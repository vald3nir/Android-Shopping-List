import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

class FirebasePlugin : Plugin<Project> {

    override fun apply(target: Project) {
        with(target) {
            dependencies {
                // Firebase Libs
                "implementation"(platform("com.google.firebase:firebase-bom:32.2.3"))
                "implementation"("com.google.firebase:firebase-storage-ktx:20.2.1")
                "implementation"("com.google.firebase:firebase-database-ktx:20.2.2")
                "implementation"("com.google.firebase:firebase-auth-ktx")

                // Firebase Auth Credentials
                "implementation"("androidx.credentials:credentials:1.3.0")
                "implementation"("androidx.credentials:credentials-play-services-auth:1.3.0")
                "implementation"("com.google.android.libraries.identity.googleid:googleid:1.1.1")

                // Google Login
                "implementation"("com.google.android.gms:play-services-auth:20.7.0")

                // Android Libraries
                "implementation"("androidx.appcompat:appcompat:1.6.1")
            }
        }
    }
}