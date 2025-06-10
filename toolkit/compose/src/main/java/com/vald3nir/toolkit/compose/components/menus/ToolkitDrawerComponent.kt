package com.vald3nir.toolkit.compose.components.menus

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.Help
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.vald3nir.toolkit.compose.components.base.DefaultSpaceHeight
import kotlinx.coroutines.launch


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DrawerComponent(
    contentTitle: String = "Drawer Title",
    content: @Composable (PaddingValues) -> Unit,
    menuContent: @Composable ColumnScope.() -> Unit,
    initialDrawerState: DrawerValue = DrawerValue.Closed,
) {
    val drawerState = rememberDrawerState(initialValue = initialDrawerState)
    val scope = rememberCoroutineScope()
    ModalNavigationDrawer(
        drawerContent = { ModalDrawerSheet(content = menuContent) },
        drawerState = drawerState
    ) {
        Scaffold(
            topBar = {
                TopAppBar(
                    title = { Text(contentTitle) },
                    navigationIcon = {
                        IconButton(onClick = {
                            scope.launch {
                                if (drawerState.isClosed) {
                                    drawerState.open()
                                } else {
                                    drawerState.close()
                                }
                            }
                        }) {
                            Icon(Icons.Default.Menu, contentDescription = "Menu")
                        }
                    }
                )
            }
        ) { innerPadding ->
            content(innerPadding)
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun Preview1() {
    DrawerComponent(
        initialDrawerState = DrawerValue.Open,
        menuContent = drawerComponentMenuContentDemo(),
        content = { }
    )
}

@Preview(showBackground = true)
@Composable
private fun Preview2() {
    DrawerComponent(
        initialDrawerState = DrawerValue.Closed,
        menuContent = drawerComponentMenuContentDemo(),
        content = { }
    )
}

@Composable
fun drawerComponentMenuContentDemo(): @Composable() (ColumnScope.() -> Unit) = {
    Column(
        modifier = Modifier
            .padding(horizontal = 16.dp)
            .verticalScroll(rememberScrollState())
    ) {
        Spacer(Modifier.height(12.dp))
        Text("Drawer Title", modifier = Modifier.padding(16.dp), style = MaterialTheme.typography.titleLarge)
        DefaultSpaceHeight()

        Text("Section 1", modifier = Modifier.padding(16.dp), style = MaterialTheme.typography.titleMedium)
        NavigationDrawerItem(
            label = { Text("Item 1") },
            selected = false,
            onClick = { /* Handle click */ }
        )
        NavigationDrawerItem(
            label = { Text("Item 2") },
            selected = false,
            onClick = { /* Handle click */ }
        )

        DefaultSpaceHeight()

        Text("Section 2", modifier = Modifier.padding(16.dp), style = MaterialTheme.typography.titleMedium)
        NavigationDrawerItem(
            label = { Text("Settings") },
            selected = false,
            icon = { Icon(Icons.Outlined.Settings, contentDescription = null) },
            badge = { Text("20") }, // Placeholder
            onClick = { /* Handle click */ }
        )
        NavigationDrawerItem(
            label = { Text("Help and feedback") },
            selected = false,
            icon = { Icon(Icons.AutoMirrored.Outlined.Help, contentDescription = null) },
            onClick = { /* Handle click */ },
        )
        Spacer(Modifier.height(12.dp))
    }
}