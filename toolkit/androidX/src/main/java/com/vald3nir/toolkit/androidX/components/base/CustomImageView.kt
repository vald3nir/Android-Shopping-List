package com.vald3nir.toolkit.androidX.components.base

import android.content.Context
import android.net.Uri
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatImageView
import androidx.core.content.ContextCompat
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.vald3nir.toolkit.helpers.extensions.toBitmap


class CustomImageView(context: Context, attr: AttributeSet?) : AppCompatImageView(context, attr) {

    fun loadImageBase64(data: String?, placeholder: Int) {
        try {
            setImageBitmap(data.toBitmap())
        } catch (e: Exception) {
            e.printStackTrace()
            loadImage(placeholder = placeholder)
        }
    }

    fun loadImage(
        uri: Uri? = null,
        url: String? = null,
        placeholder: Int,
        effect: RequestOptions? = null
    ) {
        val model = uri ?: url ?: placeholder
        Glide.with(context)
            .load(model)
            .placeholder(placeholder)
            .apply(effect ?: RequestOptions.noTransformation())
            .into(this)
    }


    fun changeDrawableColor(color: Int) {
        setColorFilter(
            ContextCompat.getColor(context, color),
            android.graphics.PorterDuff.Mode.MULTIPLY
        )
    }
}