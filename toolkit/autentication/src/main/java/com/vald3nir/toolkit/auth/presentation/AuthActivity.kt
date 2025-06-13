package com.vald3nir.toolkit.auth.presentation

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.compose.setContent
import com.vald3nir.toolkit.auth.presentation.navigaton.AuthNavHost
import com.vald3nir.toolkit.helpers.baseclasses.BaseActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AuthActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            this.AuthNavHost()
        }
    }
}

fun Context.startAuthActivity(clearStack: Boolean = true) {
    val intent = Intent(this, AuthActivity::class.java).apply {
        if (clearStack) {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        }
    }
    startActivity(intent)
}