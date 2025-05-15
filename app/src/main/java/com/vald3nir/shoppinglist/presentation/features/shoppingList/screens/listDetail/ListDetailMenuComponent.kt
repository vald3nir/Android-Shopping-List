package com.vald3nir.shoppinglist.presentation.features.shoppingList.screens.listDetail

import androidx.compose.runtime.Composable
import com.vald3nir.toolkit.compose.components.base.ToolkitIcons
import com.vald3nir.toolkit.compose.components.menus.ToolkitBottomSheetComponent
import com.vald3nir.toolkit.compose.components.menus.ToolkitItemBottomSheet
import com.vald3nir.toolkit.compose.designSystem.schema.ScreenColorSchema

@Composable
fun ListDetailMenuComponent(
    colors: ScreenColorSchema,
    onCloneList: () -> Unit = {},
    onEditListName: () -> Unit = {},
    onDeleteList: () -> Unit = {},
    onDismissRequest: () -> Unit = {},
) {
    ToolkitBottomSheetComponent(
        colors = colors,
        items = listOf(
            ToolkitItemBottomSheet.Default(
                icon = ToolkitIcons.Edit,
                title = "Editar nome da lista",
                onClick = onEditListName
            ),
            ToolkitItemBottomSheet.Default(
                icon = ToolkitIcons.ContentCopy,
                title = "Clonar lista",
                onClick = onCloneList
            ),
            ToolkitItemBottomSheet.Default(
                icon = ToolkitIcons.Delete,
                title = "Apagar lista",
                onClick = onDeleteList
            )
        ),
        onDismissRequest = onDismissRequest,
    )
}