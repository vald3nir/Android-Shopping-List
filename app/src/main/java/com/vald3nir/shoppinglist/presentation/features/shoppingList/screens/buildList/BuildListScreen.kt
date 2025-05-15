package com.vald3nir.shoppinglist.presentation.features.shoppingList.screens.buildList

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.vald3nir.shoppinglist.R
import com.vald3nir.shoppinglist.domain.dto.ItemShoppingListDTO
import com.vald3nir.shoppinglist.db.mock.MockShoppingListDTO
import com.vald3nir.shoppinglist.domain.usecases.titleListFormated
import com.vald3nir.shoppinglist.presentation.features.shoppingList.components.dialogs.BuildItemListDialog
import com.vald3nir.toolkit.compose.components.base.DefaultSpaceHeight
import com.vald3nir.toolkit.compose.components.base.ToolkitFixedButton
import com.vald3nir.toolkit.compose.components.base.ToolkitFloatingButton
import com.vald3nir.toolkit.compose.components.base.ToolkitIcons
import com.vald3nir.toolkit.compose.components.inputs.ToolkitInputTextComponent
import com.vald3nir.toolkit.compose.components.toolbars.ToolkitGenericToolbarComponent
import com.vald3nir.toolkit.compose.designSystem.AppTheme
import com.vald3nir.toolkit.compose.designSystem.DefaultThemeColors
import com.vald3nir.toolkit.compose.designSystem.LocalAppColors
import com.vald3nir.toolkit.compose.designSystem.schema.ScreenColorSchema
import com.vald3nir.toolkit.compose.templates.ToolkitBaseContainer
import com.vald3nir.toolkit.compose.templates.ToolkitBaseLoadingScreen
import com.vald3nir.toolkit.compose.templates.ToolkitEmptyStateScreen
import com.vald3nir.toolkit.helpers.utils.cap
import kotlinx.coroutines.launch

@Composable
fun BuildListScope.BuildListScreen() {
    AppTheme {

        val items = remember { mutableStateListOf<ItemShoppingListDTO>() }
        var isLoading by remember { mutableStateOf(false) }
        val snackBarHostState: SnackbarHostState = remember { SnackbarHostState() }
        val coroutineScope = rememberCoroutineScope()

        CollectUiState(
            onLoading = { isLoading = it },
            onSuccess = { onBackPressed() },
            onShowMessage = { message ->
                coroutineScope.launch {
                    snackBarHostState.showSnackbar(message)
                }
            }
        )

        if (isLoading) {
            ToolkitBaseLoadingScreen()
        } else {
            BuildListContent(
                items = items,
                snackBarHostState = snackBarHostState,
                colors = LocalAppColors.current,
                onClickSave = { items.saveList(it) },
                onAddItem = { items.addItem(it) },
                onRemove = { items.remove(it) }
            )
        }

    }
}

@Composable
private fun BuildListContent(
    scope: BuildListScope? = null,
    items: List<ItemShoppingListDTO> = listOf(),
    colors: ScreenColorSchema,
    snackBarHostState: SnackbarHostState = SnackbarHostState(),
    onAddItem: (ItemShoppingListDTO) -> Unit = {},
    onRemove: (ItemShoppingListDTO) -> Unit = {},
    onClickSave: (listAlias: String?) -> Unit = {},
) {
    var listAlias: String by remember { mutableStateOf("") }
    var showDialog: Boolean by remember { mutableStateOf(false) }
    var itemEdit: ItemShoppingListDTO? by remember { mutableStateOf(null) }


    ToolkitBaseContainer(
        backgroundColor = colors.backgroundColor,
        snackBarHostState = snackBarHostState,
        topBarContent = {
            ToolkitGenericToolbarComponent(
                title = stringResource(R.string.build_new_list_title),
                colors = colors,
                onBackClick = { scope?.onBackPressed() },
            )
        },
        content = {
            Box(modifier = Modifier.weight(1f)) {
                Column {
                    ToolkitInputTextComponent(
                        inputValue = listAlias.cap(),
                        colors = colors,
                        label = "Nome da Lista",
                        placeholder = stringResource(R.string.enter_name_for_your_list),
                        startIcon = ToolkitIcons.ShoppingBasket,
                        endIcon = ToolkitIcons.Edit,
                        onValueChange = { listAlias = it },
                    )
                    DefaultSpaceHeight()
                    if (items.isEmpty()) {
                        ToolkitEmptyStateScreen(
                            title = stringResource(R.string.empty_state_description),
                            imageVector = ToolkitIcons.ShoppingCart,
                            colors = colors,
                            onAddClick = { showDialog = true }

                        )
                    } else {
                        BuildItemsListComponent(
                            items = items,
                            colors = colors,
                            onEdit = {
                                itemEdit = it
                                showDialog = true
                            },
                            onRemove = onRemove,
                        )
                    }
                }
            }
            if (showDialog) {
                BuildItemListDialog(
                    currentItem = itemEdit,
                    colors = colors,
                    onCancel = {
                        showDialog = false
                    },
                    onConfirm = { item ->
                        itemEdit = null
                        showDialog = false
                        onAddItem(item)
                    }
                )
            }
        },
        floatingActionButton = {
            if (items.isNotEmpty()) {
                ToolkitFloatingButton(
                    containerColor = colors.highlightedBackgroundColor,
                    contentColor = colors.highlightedIconTint,
                    onClick = { showDialog = true }
                )
            }
        },
        bottomBar = {
            ToolkitFixedButton(
                colors = colors,
                label = stringResource(R.string.btn_conclude),
                onClick = { onClickSave(listAlias.titleListFormated()) }
            )
        }
    )
}

@Preview(showBackground = true, backgroundColor = 0xFF808080)
@Composable
private fun Preview() {
    BuildListContent(
        items = MockShoppingListDTO.items,
        colors = DefaultThemeColors().darkColors,
    )
}