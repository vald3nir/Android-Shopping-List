package com.vald3nir.toolkit.androidX.components.templates

import android.annotation.SuppressLint
import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.appcompat.widget.LinearLayoutCompat
import androidx.core.view.isVisible
import com.vald3nir.toolkit.androidx.R
import com.vald3nir.toolkit.androidx.databinding.LoadingButtonComponentBinding

class LoadingButtonComponent : LinearLayoutCompat {

    private val binding by lazy {
        LoadingButtonComponentBinding.inflate(
            LayoutInflater.from(context),
            this,
            true
        )
    }

    init {
        orientation = VERTICAL
        setBackgroundResource(0)
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
        val typedArray = context.obtainStyledAttributes(attrs, R.styleable.LoadingButtonComponent)
        setup(
            title = typedArray.getString(R.styleable.LoadingButtonComponent_lbTitle),
            titleColor = typedArray.getColor(
                R.styleable.LoadingButtonComponent_lbTitleColor,
                R.color.black
            ),
            backgroundColor = typedArray.getColor(
                R.styleable.LoadingButtonComponent_lbBackgroundColor,
                R.color.white
            ),
        )
    }

    private fun setup(
        title: String?,
        titleColor: Int,
        backgroundColor: Int,
    ) = with(binding) {
        button.text = title
        button.setTextColor(titleColor)
        button.setBackgroundColor(backgroundColor)
        hideLoading()
    }

    fun setOnClickButtonListener(click: () -> Unit) = with(binding) {
        button.setOnClickListener {
            showLoading(true)
            click.invoke()
        }
    }

    fun hideLoading() {
        showLoading(false)
    }

    private fun showLoading(show: Boolean) = with(binding) {
        button.isVisible = !show
        loading.isVisible = show
    }
}