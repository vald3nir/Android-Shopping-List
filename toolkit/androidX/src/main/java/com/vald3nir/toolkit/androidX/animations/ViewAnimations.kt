package com.vald3nir.toolkit.androidX.animations

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.animation.ValueAnimator
import android.view.View
import android.view.animation.AnimationUtils
import android.view.animation.AnticipateInterpolator
import android.view.animation.Interpolator
import com.vald3nir.toolkit.androidx.R

enum class TypeAnimation { ROTATION, HEART_BEAT }

var animateHeartBeat: AnimatorSet? = null

fun View.cancelAnimateHeartBeat() {
    animateHeartBeat?.cancel()
}


fun View.animateHeartBeat() {
    val interpolator: Interpolator = AnticipateInterpolator(2.8f)
    val duration = 500L

    val scaleX = ObjectAnimator.ofFloat(this, "scaleX", 1.1f)
    scaleX.interpolator = interpolator
    scaleX.repeatCount = 100
    scaleX.repeatMode = ValueAnimator.REVERSE
    scaleX.duration = duration

    val scaleY = ObjectAnimator.ofFloat(this, "scaleY", 1.1f)
    scaleY.interpolator = interpolator
    scaleY.repeatCount = 100
    scaleY.repeatMode = ValueAnimator.REVERSE
    scaleY.duration = duration

    val set1 = AnimatorSet()
    set1.playTogether(scaleX, scaleY)

    val set2 = AnimatorSet()
    set2.playTogether(scaleX, scaleY)

    animateHeartBeat = AnimatorSet()
    animateHeartBeat?.playSequentially(set1, set2)
    animateHeartBeat?.start()
}

fun View.animateRotation() {
    startAnimation(
        AnimationUtils.loadAnimation(context, R.anim.rotate_indefinitely)
    )
}