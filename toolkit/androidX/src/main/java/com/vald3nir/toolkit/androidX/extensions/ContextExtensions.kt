package com.vald3nir.toolkit.androidX.extensions

import android.content.Context
import android.content.Intent
import android.widget.Toast
import kotlin.apply

fun Context.navigateTo(clazz: Class<*>, intent: Intent.() -> Unit = {}) {
    Intent(this, clazz).apply {
        intent()
        startActivity(this)
    }
}

fun Context.showToast(message: String?): Toast {
    val toast = Toast.makeText(this, message, Toast.LENGTH_SHORT)
    toast.show()
    return toast
}