package com.vald3nir.shoppinglist.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.vald3nir.shoppinglist.R
import com.vald3nir.shoppinglist.presentation.components.lists.BuildCheckListComponent
import com.vald3nir.shoppinglist.presentation.components.lists.ItemCheckListModel
import com.vald3nir.shoppinglist.presentation.components.toolbar.BuildToolbar
import com.vald3nir.shoppinglist.presentation.theme.ShoppingListTheme

class MainActivity : ComponentActivity() {

    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ShoppingListTheme {
                val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior(rememberTopAppBarState())
                Scaffold(
                    topBar = {
                        BuildToolbar(title = stringResource(R.string.app_name), scrollBehavior = scrollBehavior)
                    },
                    modifier = Modifier.fillMaxSize()
                ) { innerPadding ->
                    val items = remember {
                        List(15) { ItemCheckListModel("Produto ${it + 1}") }
                    }
                    BuildCheckListComponent(
                        items = items,
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

