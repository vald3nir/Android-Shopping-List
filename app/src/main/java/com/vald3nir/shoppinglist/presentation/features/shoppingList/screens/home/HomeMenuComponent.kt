package com.vald3nir.shoppinglist.presentation.features.shoppingList.screens.home

import androidx.compose.runtime.Composable
import com.vald3nir.toolkit.compose.components.base.ToolkitIcons
import com.vald3nir.toolkit.compose.components.menus.ToolkitBottomSheetComponent
import com.vald3nir.toolkit.compose.components.menus.ToolkitItemBottomSheet
import com.vald3nir.toolkit.compose.designSystem.schema.ScreenColorSchema

@Composable
fun HomeMenuComponent(
    isDarkMode: Boolean = false,
    isUserLogged: Boolean = false,
    colors: ScreenColorSchema,
    onChangeTheme: (Boolean) -> Unit = {},
    onChangeUser: () -> Unit = {},
    onDismissRequest: () -> Unit = {},
) {
    ToolkitBottomSheetComponent(
        colors = colors,
        items = listOf(
            ToolkitItemBottomSheet.Default(
                icon = ToolkitIcons.AccountCircle,
                title = if (isUserLogged) "Deslogar" else "Login",
                onClick = onChangeUser
            ),
            ToolkitItemBottomSheet.Switch(
                enable = isDarkMode,
                title = "Usar dark mode",
                onCheckedChange = onChangeTheme
            ),
        ),
        onDismissRequest = onDismissRequest,
    )
}