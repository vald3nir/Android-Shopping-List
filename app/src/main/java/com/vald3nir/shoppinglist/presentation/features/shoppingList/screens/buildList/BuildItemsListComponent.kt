package com.vald3nir.shoppinglist.presentation.features.shoppingList.screens.buildList

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Divider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.vald3nir.shoppinglist.db.mock.MockShoppingListDTO
import com.vald3nir.shoppinglist.domain.dto.ItemShoppingListDTO
import com.vald3nir.toolkit.compose.components.base.ToolkitIcon
import com.vald3nir.toolkit.compose.components.base.ToolkitIcons
import com.vald3nir.toolkit.compose.components.base.ToolkitText
import com.vald3nir.toolkit.compose.components.base.defaultSpace
import com.vald3nir.toolkit.compose.designSystem.DefaultThemeColors
import com.vald3nir.toolkit.compose.designSystem.schema.ScreenColorSchema

@Composable
fun BuildItemsListComponent(
    items: List<ItemShoppingListDTO>,
    colors: ScreenColorSchema,
    onEdit: (ItemShoppingListDTO) -> Unit = {},
    onRemove: (ItemShoppingListDTO) -> Unit = {}
) {
    LazyColumn {
        val listSize = items.size
        itemsIndexed(items) { index, item ->
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(colors.backgroundColor)
                    .clickable { onEdit(item) }
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = defaultSpace),
                    verticalAlignment = Alignment.CenterVertically
                ) {

                    ToolkitText.Label(
                        text = "${index + 1}. ",
                        textColor = colors.textColor
                    )

                    ToolkitText.Label(
                        modifier = Modifier.weight(1f),
                        text = item.quantityDescription,
                        textColor = colors.itemListTextColor
                    )

                    ToolkitIcon(
                        imageVector = ToolkitIcons.RemoveCircleOutline,
                        tint = Color.Red,
                        onClick = { onRemove(item) }
                    )
                }
                if (index < listSize - 1) {
                    Divider()
                }
            }
        }
    }
}


@Preview(showBackground = true, backgroundColor = 0xFF808080)
@Composable
private fun PreviewLight() {
    BuildItemsListComponent(
        items = MockShoppingListDTO.items,
        colors = DefaultThemeColors().lightColors,
    )
}

@Preview(showBackground = true, backgroundColor = 0xFF808080)
@Composable
private fun PreviewDark() {
    BuildItemsListComponent(
        items = MockShoppingListDTO.items,
        colors = DefaultThemeColors().darkColors,
    )
}