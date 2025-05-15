package com.vald3nir.shoppinglist.presentation.features.shoppingList.components.dialogs

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.vald3nir.shoppinglist.R
import com.vald3nir.toolkit.compose.components.base.ToolkitIcons
import com.vald3nir.toolkit.compose.components.base.ToolkitText
import com.vald3nir.toolkit.compose.components.inputs.ToolkitInputTextComponent
import com.vald3nir.toolkit.compose.designSystem.DefaultThemeColors
import com.vald3nir.toolkit.compose.designSystem.schema.ScreenColorSchema
import com.vald3nir.toolkit.helpers.utils.cap

@Composable
fun EditListNameDialog(
    currentListName: String = "",
    colors: ScreenColorSchema,
    onConfirm: (String) -> Unit = {},
    onCancel: () -> Unit = {},
    onDismissRequest: () -> Unit = {}
) {
    var inputValue: String by remember { mutableStateOf(currentListName) }
    AlertDialog(
        containerColor = colors.dialogBackgroundColor,
        onDismissRequest = onDismissRequest,
        title = {
            ToolkitText.Title(
                text = "Altere o nome da lista",
                textColor = colors.dialogTextColor
            )
        },
        text = {
            ToolkitInputTextComponent(
                inputValue = inputValue,
                colors = colors,
                useTransparentBackend = true,
                startIcon = ToolkitIcons.ShoppingBasket,
                endIcon = ToolkitIcons.Edit,
                onValueChange = { inputValue = it },
            )
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
                    onConfirm(inputValue.cap())
                }) {
                ToolkitText.Label(
                    text = stringResource(R.string.alter),
                    textColor = colors.dialogConfirmBtnTextColor,
                )
            }
        },
    )
}


@Preview(showBackground = true)
@Composable
private fun PreviewLight() {
    EditListNameDialog(
        currentListName = "Minha lista",
        colors = DefaultThemeColors().lightColors,
    )
}

@Preview(showBackground = true)
@Composable
private fun PreviewDark() {
    EditListNameDialog(
        currentListName = "Minha lista",
        colors = DefaultThemeColors().darkColors,
    )
}