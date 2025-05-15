package com.vald3nir.shoppinglist.presentation.features.shoppingList.screens.editItemList

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableDoubleStateOf
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.vald3nir.shoppinglist.R
import com.vald3nir.shoppinglist.domain.dto.ItemShoppingListDTO
import com.vald3nir.shoppinglist.presentation.features.shoppingList.components.itemList.InputProductNameComponent
import com.vald3nir.toolkit.compose.components.base.DefaultSpaceHeight
import com.vald3nir.toolkit.compose.components.base.DefaultSpaceWidth
import com.vald3nir.toolkit.compose.components.base.HalfSpaceWidth
import com.vald3nir.toolkit.compose.components.base.ToolkitFixedButton
import com.vald3nir.toolkit.compose.components.base.ToolkitIcon
import com.vald3nir.toolkit.compose.components.base.ToolkitIcons
import com.vald3nir.toolkit.compose.components.base.ToolkitSwitch
import com.vald3nir.toolkit.compose.components.base.ToolkitText
import com.vald3nir.toolkit.compose.components.dialogs.ToolkitAlertDialogComponent
import com.vald3nir.toolkit.compose.components.inputs.ToolkitIntegerInputField
import com.vald3nir.toolkit.compose.components.inputs.ToolkitMonetaryInputField
import com.vald3nir.toolkit.compose.components.toolbars.ToolkitGenericToolbarComponent
import com.vald3nir.toolkit.compose.designSystem.AppTheme
import com.vald3nir.toolkit.compose.designSystem.LocalAppColors
import com.vald3nir.toolkit.compose.designSystem.schema.ScreenColorSchema
import com.vald3nir.toolkit.compose.templates.ToolkitBaseContainer
import com.vald3nir.toolkit.helpers.utils.cap
import com.vald3nir.toolkit.helpers.utils.orFalse
import com.vald3nir.toolkit.helpers.utils.orZero
import java.math.BigDecimal

@Composable
fun EditItemListScope.EditItemListScreen(itemListID: Long?) {
    AppTheme {
        val itemShoppingListState by viewModel.itemShoppingList.collectAsState()
        val colors = LocalAppColors.current

        CollectUiState(
            onSuccess = { onBackPressed() }
        )

        LoadItemShoppingList(itemListID)

        EditShoppingItemListContent(
            scope = this,
            itemShoppingList = itemShoppingListState,
            colors = colors,
            onUpdateItem = { updateItem(it) },
            onDeleteItem = { deleteItem(it) }
        )
    }
}

@Composable
fun EditShoppingItemListContent(
    scope: EditItemListScope? = null,
    itemShoppingList: ItemShoppingListDTO?,
    colors: ScreenColorSchema,
    snackBarHostState: SnackbarHostState = SnackbarHostState(),
    onDeleteItem: (ItemShoppingListDTO?) -> Unit = {},
    onUpdateItem: (ItemShoppingListDTO?) -> Unit = {},
) {
    var itemTitle by remember { mutableStateOf("") }
    var itemUnitPrice by remember { mutableDoubleStateOf(0.0) }
    var itemQuantity by remember { mutableIntStateOf(0) }
    var itemAdded by remember { mutableStateOf(false) }
    var showDialogRemoveItem by remember { mutableStateOf(false) }

    LaunchedEffect(itemShoppingList) {
        itemTitle = itemShoppingList?.title.orEmpty()
        itemUnitPrice = itemShoppingList?.unitPrice ?: 0.0
        itemQuantity = itemShoppingList?.quantity.orZero()
        itemAdded = itemShoppingList?.isAdd.orFalse()
    }

    ToolkitBaseContainer(
        backgroundColor = colors.backgroundColor,
        snackBarHostState = snackBarHostState,
        topBarContent = {
            ToolkitGenericToolbarComponent(
                title = "Edição do Item",
                colors = colors,
                onBackClick = { scope?.onBackPressed() },
                genericContent = {
                    ToolkitIcon(
                        imageVector = ToolkitIcons.Delete,
                        tint = colors.toolbarIconTint,
                        onClick = {
                            showDialogRemoveItem = true
                        }
                    )
                }
            )
        },
        content = {
            Column {

                println("Valdenir: itemAdded: $itemAdded")

                InputProductNameComponent(
                    inputValue = itemTitle,
                    colors = colors,
                    onSelected = { itemTitle = it.cap() }
                )

                DefaultSpaceHeight()

                ToolkitIntegerInputField(
                    inputValue = itemQuantity.toString(),
                    colors = colors,
                    label = "Quantidade",
                    placeholder = "Digite a quantidade deste produto",
                    startIcon = ToolkitIcons.Pin,
                    endIcon = ToolkitIcons.Edit,
                    onValueChange = {
                        itemQuantity = it
                    },
                )

                DefaultSpaceHeight()

                ToolkitMonetaryInputField(
                    valueInCents = BigDecimal(itemUnitPrice.times(100)),
                    colors = colors,
                    label = "Preço Unitário",
                    placeholder = "Digite o preço do produto",
                    startIcon = ToolkitIcons.Paid,
                    endIcon = ToolkitIcons.Edit,
                    onValueChange = {
                        itemUnitPrice = it
                    },
                )

                DefaultSpaceHeight()

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable { itemAdded = !itemAdded },
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    ToolkitText.Label(
                        modifier = Modifier.weight(1f),
                        text = "Adicionar ao carrinho?",
                        textColor = colors.textColor
                    )
                    DefaultSpaceWidth()
                    ToolkitText.Label(
                        text = "Não",
                        textColor = colors.textColor
                    )
                    HalfSpaceWidth()
                    ToolkitSwitch(
                        startEnable = itemAdded,
                        colors = colors,
                        onCheckedChange = { itemAdded = it },
                    )
                    HalfSpaceWidth()
                    ToolkitText.Label(
                        text = "Sim",
                        textColor = colors.textColor
                    )
                }

                if (showDialogRemoveItem) {
                    ToolkitAlertDialogComponent(
                        title = "Deseja remover o item?",
                        btnConfirmLabel = "Remover",
                        btnCancelLabel = "Cancelar",
                        colors = colors,
                        onConfirm = {
                            onDeleteItem(itemShoppingList)
                            showDialogRemoveItem = false
                        },
                        onCancel = {
                            showDialogRemoveItem = false
                        }
                    )
                }
            }
        },
        bottomBar = {
            ToolkitFixedButton(
                colors = colors,
                label = stringResource(R.string.confirm),
                onClick = {
                    onUpdateItem.invoke(
                        itemShoppingList?.copy(
                            title = itemTitle,
                            unitPrice = itemUnitPrice,
                            quantity = itemQuantity,
                            isAdd = itemAdded
                        )
                    )
                }
            )
        }
    )
}