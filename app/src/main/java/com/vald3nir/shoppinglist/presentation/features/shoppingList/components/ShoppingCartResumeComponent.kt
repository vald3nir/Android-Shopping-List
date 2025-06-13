package com.vald3nir.shoppinglist.presentation.features.shoppingList.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.vald3nir.shoppinglist.R
import com.vald3nir.shoppinglist.db.mock.MockShoppingListDTO
import com.vald3nir.shoppinglist.domain.dto.ItemShoppingListDTO
import com.vald3nir.shoppinglist.domain.usecases.totalValue
import com.vald3nir.toolkit.compose.components.base.DefaultSpaceHeight
import com.vald3nir.toolkit.compose.components.base.ToolkitFixedButton
import com.vald3nir.toolkit.compose.components.base.ToolkitRow
import com.vald3nir.toolkit.compose.components.base.ToolkitText
import com.vald3nir.toolkit.compose.designSystem.DefaultThemeColors
import com.vald3nir.toolkit.compose.designSystem.schema.ScreenColorSchema

@Composable
fun ShoppingCartResumeComponent(
    list: List<ItemShoppingListDTO> = listOf(),
    colors: ScreenColorSchema,
    onClickBtnContinue: () -> Unit = {},
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(colors.backgroundColor),
    ) {
        ToolkitRow {
            ToolkitText.Subtitle(
                modifier = Modifier
                    .weight(1f)
                    .wrapContentWidth(Alignment.Start),
                text = stringResource(R.string.shopping_list_total_value),
                textColor = colors.textColor
            )
            ToolkitText.Subtitle(
                modifier = Modifier
                    .weight(1f)
                    .wrapContentWidth(Alignment.End),
                text = list.totalValue(),
                textColor = colors.textColor
            )
        }
        ToolkitFixedButton(
            colors = colors,
            label = stringResource(R.string.btn_conclude),
            onClick = onClickBtnContinue
        )
    }
}

@Preview(showBackground = true, backgroundColor = 0xFF808080)
@Composable
private fun Preview() {
    val colors = DefaultThemeColors()
    val list = MockShoppingListDTO.lists().first().items
    Column {
        ShoppingCartResumeComponent(list = list, colors = colors.lightColors)
        DefaultSpaceHeight()
        ShoppingCartResumeComponent(list = list, colors = colors.darkColors)
    }
}