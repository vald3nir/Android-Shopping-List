package com.vald3nir.shoppinglist.presentation.features.boot

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.hilt.navigation.compose.hiltViewModel
import com.vald3nir.shoppinglist.presentation.BaseActivity
import com.vald3nir.shoppinglist.presentation.features.shoppingList.startShoppingListActivity
import com.vald3nir.toolkit.auth.presentation.startAuthActivity
import com.vald3nir.toolkit.compose.templates.ToolkitBaseLoadingScreen
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay

@AndroidEntryPoint
internal class BootActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BootScreenContent { isUserLogged ->
                if (isUserLogged) {
                    startShoppingListActivity()
                } else {
                    startAuthActivity()
                }
            }
        }
    }
}

@Composable
private fun BootScreenContent(onLoadUserStatus: (isUserLogged: Boolean) -> Unit = {}) {
    val viewModel = hiltViewModel<BootViewModel>()
    LaunchedEffect(Unit) {
        delay(2000)//todo valdenir add validação do banco de dados
        onLoadUserStatus(viewModel.isUserLogged())
    }
    ToolkitBaseLoadingScreen()
}