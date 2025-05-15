package com.vald3nir.shoppinglist.presentation.features.boot

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import com.vald3nir.shoppinglist.presentation.BaseActivity
import com.vald3nir.shoppinglist.presentation.features.shoppingList.startShoppingListActivity
import com.vald3nir.toolkit.auth.presentation.startAuthActivity
import com.vald3nir.toolkit.compose.templates.ToolkitBaseLoadingScreen
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
internal class BootActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BootScreenContent()
        }
    }
}

@Composable
private fun BootScreenContent() {
    val context = LocalContext.current
    val viewModel = hiltViewModel<BootViewModel>()
    viewModel.setupFirebaseDB()
    LaunchedEffect(Unit) {
        viewModel.checkFirebaseDB(
            context = context,
            onRedirectToAuth = { context.startAuthActivity() },
            onRedirectToHome = { context.startShoppingListActivity() }
        )
    }
    ToolkitBaseLoadingScreen()
}