package com.vald3nir.android.firebase.auth

//import com.facebook.*
import android.content.Context
import android.content.pm.PackageManager
import android.util.Base64
import android.util.Log
import java.security.MessageDigest

object FacebookAuthenticator {

//    fun buildCallbackManager() = CallbackManager.Factory.create() // call the activity's onCreate method
//
//    fun login(activity: Activity, callbackManager: CallbackManager) {
//        val loginManager = LoginManager.getInstance()
//        loginManager.registerCallback(callbackManager, object : FacebookCallback<LoginResult> {
//
//            override fun onSuccess(result: LoginResult) {
//                val token = result.accessToken.token
//                val credential = FacebookAuthProvider.getCredential(token)
//
//                FirebaseAuth.getInstance().signInWithCredential(credential)
//                    .addOnCompleteListener { task ->
//                        if (task.isSuccessful) {
//
//                        } else {
//
//                        }
//                    }
//            }
//
//            override fun onCancel() = Unit
//
//            override fun onError(error: FacebookException) {
//                error.printStackTrace()
//            }
//        })
//        loginManager.logInWithReadPermissions(activity, listOf("email", "public_profile"))
//    }

    fun printKeyHash(context: Context) {
        try {
            val packageInfo = context.packageManager.getPackageInfo(
                context.packageName, PackageManager.GET_SIGNING_CERTIFICATES // Para API 28+
            )

            val signatures = if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.P) {
                packageInfo.signingInfo?.apkContentsSigners
            } else {
                // Deprecated, mas ainda funciona para versões anteriores
                @Suppress("DEPRECATION")
                packageInfo.signatures
            }

            if (signatures != null) {
                for (signature in signatures) {
                    val md = MessageDigest.getInstance("SHA")
                    md.update(signature.toByteArray())
                    val keyHash = Base64.encodeToString(md.digest(), Base64.NO_WRAP)
                    Log.d("KeyHash", "KeyHash: $keyHash")
                }
            }

        } catch (e: Exception) {
            Log.e("KeyHash", "Erro ao gerar KeyHash", e)
        }
    }
}