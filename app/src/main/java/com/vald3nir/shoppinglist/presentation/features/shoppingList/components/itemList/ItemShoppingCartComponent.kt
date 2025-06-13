package com.vald3nir.shoppinglist.presentation.features.shoppingList.components.itemList

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.vald3nir.shoppinglist.db.mock.MockShoppingListDTO
import com.vald3nir.shoppinglist.domain.dto.ItemShoppingListDTO
import com.vald3nir.toolkit.compose.components.base.DefaultSpaceHeight
import com.vald3nir.toolkit.compose.components.base.DefaultSpaceWidth
import com.vald3nir.toolkit.compose.components.base.ToolkitCard
import com.vald3nir.toolkit.compose.components.base.ToolkitCheckBox
import com.vald3nir.toolkit.compose.components.base.ToolkitIcon
import com.vald3nir.toolkit.compose.components.base.ToolkitIcons
import com.vald3nir.toolkit.compose.components.base.ToolkitRow
import com.vald3nir.toolkit.compose.components.base.ToolkitText
import com.vald3nir.toolkit.compose.components.base.defaultSpace
import com.vald3nir.toolkit.compose.designSystem.DefaultThemeColors
import com.vald3nir.toolkit.compose.designSystem.schema.ScreenColorSchema

@Composable
fun ItemShoppingCartComponent(
    item: ItemShoppingListDTO,
    colors: ScreenColorSchema,
    onClickSeeDetails: (ItemShoppingListDTO) -> Unit = {},
    onUpdateItem: (ItemShoppingListDTO) -> Unit = {}
) {
    ToolkitCard(
        backgroundColor = colors.itemListBackgroundColor,
        onClick = { onClickSeeDetails(item) }) {
        ToolkitRow {
            ToolkitCheckBox(
                colors = colors,
                checked = item.isAdd,
                onCheckedChange = {
                    onUpdateItem(item.copy(isAdd = !item.isAdd))
                },
            )
            DefaultSpaceWidth()
            Column(modifier = Modifier.weight(1f)) {
                ToolkitText.Subtitle(
                    text = item.title.toString(),
                    textColor = colors.itemListTextColor
                )
                ToolkitText.Label(
                    text = item.description,
                    textColor = if (item.hasWarning) Color.Red else colors.itemListTextColor
                )
            }
            DefaultSpaceWidth()
            ToolkitIcon(
                imageVector = ToolkitIcons.ChevronRight,
                tint = colors.itemListIconTint,
                onClick = { onClickSeeDetails(item) }
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun Preview() {
    val items = MockShoppingListDTO.items.subList(0, 2)
    val colors = DefaultThemeColors()
    Column(modifier = Modifier.padding(defaultSpace)) {
        items.forEach {
            ItemShoppingCartComponent(item = it, colors = colors.lightColors)
            DefaultSpaceHeight()
        }
        items.forEach {
            ItemShoppingCartComponent(item = it, colors = colors.darkColors)
            DefaultSpaceHeight()
        }
    }
}