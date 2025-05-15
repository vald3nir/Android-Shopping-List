package com.vald3nir.shoppinglist.presentation.features.shoppingList.components.dialogs

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.vald3nir.shoppinglist.R
import com.vald3nir.shoppinglist.domain.dto.ItemShoppingListDTO
import com.vald3nir.shoppinglist.presentation.features.shoppingList.components.itemList.InputProductNameComponent
import com.vald3nir.toolkit.compose.components.base.DefaultSpaceHeight
import com.vald3nir.toolkit.compose.components.base.ToolkitIcons
import com.vald3nir.toolkit.compose.components.base.ToolkitText
import com.vald3nir.toolkit.compose.components.inputs.ToolkitIntegerInputField
import com.vald3nir.toolkit.compose.designSystem.DefaultThemeColors
import com.vald3nir.toolkit.compose.designSystem.schema.ScreenColorSchema
import com.vald3nir.toolkit.helpers.utils.cap

@Composable
fun BuildItemListDialog(
    currentItem: ItemShoppingListDTO? = null,
    colors: ScreenColorSchema,
    onConfirm: (ItemShoppingListDTO) -> Unit = {},
    onCancel: () -> Unit = {},
    onDismissRequest: () -> Unit = {}
) {
    var inputValue: String by remember { mutableStateOf(currentItem?.title ?: "") }
    var inputQuantity: Int by remember { mutableIntStateOf(currentItem?.quantity ?: 1) }

    AlertDialog(
        containerColor = colors.dialogBackgroundColor,
        onDismissRequest = onDismissRequest,
        title = {
            ToolkitText.Title(
                text = "Adicionar na lista de compras",
                textColor = colors.dialogTextColor
            )
        },
        text = {
            Column {

                InputProductNameComponent(
                    useTransparentBackend = true,
                    inputValue = inputValue,
                    colors = colors,
                    onSelected = { inputValue = it.cap() },
                )

                DefaultSpaceHeight()

                ToolkitIntegerInputField(
                    inputValue = inputQuantity.toString(),
                    colors = colors,
                    useTransparentBackend = true,
                    label = "Quantidade de itens",
                    placeholder = "Quantidade",
                    startIcon = ToolkitIcons.Pin,
                    endIcon = ToolkitIcons.Edit,
                    onValueChange = { inputQuantity = it },
                )
            }
        },
        dismissButton = {
            TextButton(onClick = onCancel) {
                ToolkitText.Label(
                    text = stringResource(R.string.cancel),
                    textColor = colors.dialogCancelBtnTextColor
                )
            }
        },
        confirmButton = {
            Button(
                enabled = inputValue.isNotBlank(),
                colors = ButtonDefaults.buttonColors(
                    containerColor = colors.dialogConfirmBtnBackgroundColor,
                    contentColor = colors.dialogConfirmBtnBackgroundColor
                ),
                onClick = {
                    onConfirm(
                        (currentItem ?: ItemShoppingListDTO()).copy(
                            title = inputValue.cap(),
                            quantity = inputQuantity,
                        )
                    )
                }) {
                ToolkitText.Label(
                    text = stringResource(R.string.add),
                    textColor = colors.dialogConfirmBtnTextColor,
                )
            }
        },
    )
}


@Preview(showBackground = true)
@Composable
private fun PreviewLight() {
    BuildItemListDialog(
        colors = DefaultThemeColors().lightColors,
    )
}

@Preview(showBackground = true)
@Composable
private fun PreviewDark() {
    BuildItemListDialog(
        colors = DefaultThemeColors().darkColors,
    )
}