package com.vald3nir.android.firebase.utils

import android.util.Base64
import java.security.SecureRandom

fun generateNonce(): String {
    val nonce = ByteArray(16)
    SecureRandom().nextBytes(nonce)
    return Base64.encodeToString(nonce, Base64.NO_WRAP or Base64.URL_SAFE)
}