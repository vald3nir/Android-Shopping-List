package com.vald3nir.toolkit.androidX.components.templates

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import com.vald3nir.toolkit.androidX.animations.TypeAnimation
import com.vald3nir.toolkit.androidX.animations.animateHeartBeat
import com.vald3nir.toolkit.androidX.animations.animateRotation
import com.vald3nir.toolkit.androidx.R
import com.vald3nir.toolkit.androidx.databinding.ComponentLoadingScreenBinding

class LoadingScreenDialog(
    context: Context,
    private val title: String,
    private val icon: Int,
    private val typeAnimation: TypeAnimation = TypeAnimation.ROTATION
) : Dialog(context, R.style.full_screen_dialog) {

    private lateinit var binding: ComponentLoadingScreenBinding

    init {
        setCancelable(true)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window?.setBackgroundDrawable(ColorDrawable(Color.BLACK))
        binding = ComponentLoadingScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setup()
    }

    private fun setup() = with(binding) {
        txvTitle.text = title
        imvLogo.loadImage(placeholder = icon)
        showAnimation(typeAnimation)
    }

    private fun showAnimation(typeAnimation: TypeAnimation) = with(binding) {
        when (typeAnimation) {
            TypeAnimation.ROTATION -> imvLogo.animateRotation()
            TypeAnimation.HEART_BEAT -> imvLogo.animateHeartBeat()
        }
    }
}

