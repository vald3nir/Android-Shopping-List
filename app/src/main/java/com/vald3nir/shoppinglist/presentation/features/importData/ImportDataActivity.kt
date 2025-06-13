package com.vald3nir.shoppinglist.presentation.features.importData

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import com.vald3nir.shoppinglist.presentation.CustomActivity
import com.vald3nir.toolkit.auth.presentation.startAuthActivity
import com.vald3nir.toolkit.compose.templates.ToolkitBaseLoadingScreen
import com.vald3nir.toolkit.helpers.baseclasses.BaseActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
internal class ImportDataActivity : CustomActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ImportDataScreen()
        }
    }
}

fun BaseActivity.startImportDataActivity(listener: (Intent?) -> Unit) {
    val intent = Intent(this, ImportDataActivity::class.java)
    launchIntent(intent, listener)
}

@Composable
private fun BaseActivity.ImportDataScreen() {
    val context = LocalContext.current
    val viewModel = hiltViewModel<ImportDataViewModel>()
    LaunchedEffect(Unit) {
        viewModel.importDatabaseAndRedirect(
            onRedirectToAuth = { context.startAuthActivity() },
            onFinished = {
                setResult(Activity.RESULT_OK, intent)
                finish()
            }
        )
    }
    ToolkitBaseLoadingScreen()
}