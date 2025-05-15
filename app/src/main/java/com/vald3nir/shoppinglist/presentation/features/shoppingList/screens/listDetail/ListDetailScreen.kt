package com.vald3nir.shoppinglist.presentation.features.shoppingList.screens.listDetail

import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.tooling.preview.Preview
import com.vald3nir.shoppinglist.domain.dto.ItemShoppingListDTO
import com.vald3nir.shoppinglist.domain.dto.ShoppingListDTO
import com.vald3nir.shoppinglist.db.mock.MockShoppingListDTO
import com.vald3nir.shoppinglist.presentation.features.shoppingList.components.ShoppingCartResumeComponent
import com.vald3nir.shoppinglist.presentation.features.shoppingList.components.dialogs.BuildItemListDialog
import com.vald3nir.shoppinglist.presentation.features.shoppingList.components.dialogs.EditListNameDialog
import com.vald3nir.toolkit.compose.components.base.ToolkitFloatingButton
import com.vald3nir.toolkit.compose.components.base.ToolkitIcon
import com.vald3nir.toolkit.compose.components.base.ToolkitIcons
import com.vald3nir.toolkit.compose.components.dialogs.ToolkitAlertDialogComponent
import com.vald3nir.toolkit.compose.components.toolbars.ToolkitGenericToolbarComponent
import com.vald3nir.toolkit.compose.designSystem.AppTheme
import com.vald3nir.toolkit.compose.designSystem.DefaultThemeColors
import com.vald3nir.toolkit.compose.designSystem.LocalAppColors
import com.vald3nir.toolkit.compose.designSystem.schema.ScreenColorSchema
import com.vald3nir.toolkit.compose.templates.ToolkitBaseContainer
import com.vald3nir.toolkit.compose.templates.ToolkitEmptyStateScreen
import kotlinx.coroutines.launch

@Composable
fun ListDetailScope.ListDetailScreen(shoppingListID: Long? = null) {
    AppTheme {
        this.shoppingListID = shoppingListID
        val colors = LocalAppColors.current
        val shoppingListState: ShoppingListDTO? by viewModel.shoppingList.collectAsState()
        val snackBarHostState = remember { SnackbarHostState() }
        val coroutineScope = rememberCoroutineScope()

        LoadShoppingList(shoppingListID)

        CollectUiState(
            onSuccess = { onBackPressed() },
            onShowMessage = { message ->
                coroutineScope.launch {
                    snackBarHostState.showSnackbar(message)
                }
            }
        )

        ShoppingListDetailContent(
            scope = this,
            shoppingList = shoppingListState,
            colors = colors,
            snackBarHostState = snackBarHostState,
            onClickEditItem = onClickEditItem,
            onAddNewItem = onAddNewItem,
            onUpdateItem = onUpdateItem,
            onEditListName = onEditListName,
        )
    }
}

@Composable
private fun ShoppingListDetailContent(
    scope: ListDetailScope? = null,
    colors: ScreenColorSchema,
    shoppingList: ShoppingListDTO?,
    onClickEditItem: (ItemShoppingListDTO) -> Unit = {},
    onUpdateItem: (ItemShoppingListDTO) -> Unit = {},
    onAddNewItem: (ItemShoppingListDTO) -> Unit = {},
    onEditListName: (String) -> Unit = {},
    snackBarHostState: SnackbarHostState = SnackbarHostState(),
) {

    var showMenu: Boolean by remember { mutableStateOf(false) }
    var showDialogAddItem: Boolean by remember { mutableStateOf(false) }
    var showDialogEditListName: Boolean by remember { mutableStateOf(false) }
    var showDialogDeleteList: Boolean by remember { mutableStateOf(false) }

    ToolkitBaseContainer(
        backgroundColor = colors.backgroundColor,
        snackBarHostState = snackBarHostState,
        topBarContent = {
            ToolkitGenericToolbarComponent(
                title = shoppingList?.title.orEmpty(),
                colors = colors,
                onBackClick = { scope?.onBackPressed() },
                genericContent = {
                    ToolkitIcon(
                        imageVector = ToolkitIcons.MoreVert,
                        tint = colors.toolbarIconTint,
                        onClick = {
                            showMenu = true
                        }
                    )
                }
            )
        },
        content = {
            if (shoppingList?.items.orEmpty().isEmpty()) {
                ToolkitEmptyStateScreen(
                    title = "Sem itens cadastrados",
                    imageVector = ToolkitIcons.Inbox,
                    colors = colors
                )
            } else {
                ItemsListDetailComponent(
                    shoppingList = shoppingList,
                    onClickSeeDetails = onClickEditItem,
                    colors = colors,
                    onUpdateItem = onUpdateItem
                )
            }
            if (showMenu) {
                ListDetailMenuComponent(
                    colors = colors,
                    onDismissRequest = { showMenu = false },
                    onEditListName = { showDialogEditListName = true },
                    onCloneList = { scope?.cloneList(shoppingList) },
                    onDeleteList = { showDialogDeleteList = true },
                )
            }
            if (showDialogAddItem) {
                BuildItemListDialog(
                    colors = colors,
                    onCancel = {
                        showDialogAddItem = false
                    },
                    onConfirm = { item ->
                        showDialogAddItem = false
                        onAddNewItem(item)
                    }
                )
            }
            if (showDialogEditListName) {
                EditListNameDialog(
                    currentListName = shoppingList?.title.orEmpty(),
                    colors = colors,
                    onCancel = {
                        showDialogEditListName = false
                    },
                    onConfirm = { name ->
                        showDialogEditListName = false
                        onEditListName(name)
                    }
                )
            }
            if (showDialogDeleteList) {
                ToolkitAlertDialogComponent(
                    title = "Deseja remover a lista?",
                    btnConfirmLabel = "Remover",
                    btnCancelLabel = "Cancelar",
                    colors = colors,
                    onConfirm = {
                        scope?.deleteList()
                        showDialogDeleteList = false
                    },
                    onCancel = {
                        showDialogDeleteList = false
                    }
                )
            }
        },
        floatingActionButton = {
            ToolkitFloatingButton(
                containerColor = colors.highlightedBackgroundColor,
                contentColor = colors.highlightedIconTint,
                onClick = { showDialogAddItem = true }
            )
        },
        bottomBar = {
            ShoppingCartResumeComponent(colors = colors, list = shoppingList?.items.orEmpty(), onClickBtnContinue = { scope?.onBackPressed() })
        }
    )
}


@Preview(showBackground = true)
@Composable
private fun Preview() {
    ShoppingListDetailContent(
        shoppingList = MockShoppingListDTO.lists().first(),
        colors = DefaultThemeColors().lightColors
    )
}