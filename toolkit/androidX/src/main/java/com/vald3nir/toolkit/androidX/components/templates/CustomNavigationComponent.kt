package com.vald3nir.toolkit.androidX.components.templates

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.appcompat.widget.LinearLayoutCompat
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import com.vald3nir.toolkit.androidx.R
import com.vald3nir.toolkit.androidx.databinding.CustomNavigationComponentBinding

class CustomNavigationComponent : LinearLayoutCompat {

    private val binding by lazy {
        CustomNavigationComponentBinding.inflate(
            LayoutInflater.from(context),
            this,
            true
        )
    }

    init {
        orientation = VERTICAL
    }

    constructor(context: Context) : super(context) {
        initAttrs(null)
    }

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {
        initAttrs(attrs)
    }

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context, attrs, defStyleAttr
    ) {
        initAttrs(attrs)
    }

    @SuppressLint("Recycle", "CustomViewStyleable", "ResourceAsColor")
    private fun initAttrs(attrs: AttributeSet?) = with(binding) {
        if (attrs == null) return
        val typedArray =
            context.obtainStyledAttributes(attrs, R.styleable.CustomNavigationComponent)
        setTitle(
            title = typedArray.getString(R.styleable.CustomNavigationComponent_nvTitle),
            color = typedArray.getColor(
                R.styleable.CustomNavigationComponent_nvTitleColor,
                R.color.white
            )
        )
        btnBack.isVisible = typedArray.getBoolean(R.styleable.CustomNavigationComponent_nvShowBackButton, true)
        btnClose.isVisible = typedArray.getBoolean(R.styleable.CustomNavigationComponent_nvShowCloseButton, false)
    }

    fun setTitle(title: String? = null, titleID: Int? = null, color: Int? = null) =
        with(binding.txvTitle) {
            titleID?.let { text = context.getString(it) }.also { text = title }
            color?.let { setTextColor(it) }
        }

    fun updateIconsCor(color: Int) = with(binding) {
        btnBack.changeDrawableColor(color)
        btnClose.changeDrawableColor(color)
    }

    fun enableClickEvents(activity: Activity?) = with(binding) {
        btnBack.setOnClickListener { activity?.onBackPressed() }
        btnClose.setOnClickListener { activity?.finish() }
    }

    fun enableClickEvents(fragment: Fragment) = with(binding) {
        btnBack.setOnClickListener { fragment.activity?.onBackPressed() }
        btnClose.setOnClickListener { fragment.activity?.finish() }
    }
}