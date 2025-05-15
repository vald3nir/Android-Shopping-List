package com.vald3nir.toolkit.androidX.extensions

import android.widget.TextView
import androidx.core.content.ContextCompat

fun TextView.setTextColorTo(color: Int) {
    setTextColor(ContextCompat.getColor(context, color))
}

fun TextView.formatStringResources(stringID: Int, value: Any?): String? {
    return context.formatStringResources(stringID, value)
}