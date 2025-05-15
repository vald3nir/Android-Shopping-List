package com.vald3nir.toolkit.androidX.extensions

import android.content.Context
import android.view.View
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment


fun View?.changeColor(color: Int) {
    this?.context?.let { ContextCompat.getColor(it, color) }
}

fun Context?.formatStringResources(stringID: Int, value: Any?): String? {
    this?.let {
        try {
            return String.format(it.getString(stringID), value)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
    return null
}

fun Fragment.formatStringResources(stringID: Int, value: Any?): String? {
    return context.formatStringResources(stringID, value)
}





