package com.vald3nir.shoppinglist.presentation.features.shoppingList.components.itemList

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.vald3nir.shoppinglist.domain.dto.ShoppingListDTO
import com.vald3nir.shoppinglist.db.mock.MockShoppingListDTO
import com.vald3nir.toolkit.compose.components.base.DefaultSpaceHeight
import com.vald3nir.toolkit.compose.components.base.HalfSpaceHeight
import com.vald3nir.toolkit.compose.components.base.ToolkitCard
import com.vald3nir.toolkit.compose.components.base.ToolkitIcon
import com.vald3nir.toolkit.compose.components.base.ToolkitIcons
import com.vald3nir.toolkit.compose.components.base.ToolkitText
import com.vald3nir.toolkit.compose.components.base.defaultSpace
import com.vald3nir.toolkit.compose.designSystem.DefaultThemeColors
import com.vald3nir.toolkit.compose.designSystem.schema.ScreenColorSchema

@Composable
fun ShoppingListResumeSectionComponent(
    list: ShoppingListDTO,
    colors: ScreenColorSchema,
    onClick: () -> Unit = {}
) {
    ToolkitCard(
        backgroundColor = colors.itemListBackgroundColor,
        onClick = onClick,
        content = {
            Row(
                modifier = Modifier
                    .padding(defaultSpace)
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column(modifier = Modifier.weight(1f)) {

                    ToolkitText.Title(
                        text = list.title.toString(),
                        textColor = colors.itemListTextColor
                    )

                    HalfSpaceHeight()

                    ToolkitText.Subtitle(
                        text = list.date.toString(),
                        textColor = colors.itemListTextColor
                    )
                }

                ToolkitIcon(
                    imageVector = ToolkitIcons.ArrowIndicatorRight,
                    tint = colors.itemListIconTint
                )
            }
        })
}

@Preview(showBackground = true)
@Composable
private fun Preview() {
    val list = MockShoppingListDTO.lists().first()
    val colors = DefaultThemeColors()
    Column(modifier = Modifier.padding(defaultSpace)) {
        ShoppingListResumeSectionComponent(list, colors.lightColors)
        DefaultSpaceHeight()
        ShoppingListResumeSectionComponent(list, colors.darkColors)
    }
}