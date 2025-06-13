package com.vald3nir.shoppinglist.presentation

import android.graphics.Color
import android.os.Bundle
import com.vald3nir.shoppinglist.BuildConfig
import com.vald3nir.toolkit.compose.extensions.updateStatusBarColor
import com.vald3nir.toolkit.helpers.baseclasses.BaseActivity

abstract class CustomActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (BuildConfig.FLAVOR == "dev") {
            updateStatusBarColor(statusBarColor = Color.RED)
        }
    }
}