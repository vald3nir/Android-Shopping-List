package com.vald3nir.shoppinglist.presentation.features.shoppingList.screens.listDetail

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.vald3nir.shoppinglist.R
import com.vald3nir.shoppinglist.db.mock.MockShoppingListDTO
import com.vald3nir.shoppinglist.domain.dto.ItemShoppingListDTO
import com.vald3nir.shoppinglist.domain.dto.ShoppingListDTO
import com.vald3nir.shoppinglist.domain.usecases.splitItems
import com.vald3nir.shoppinglist.presentation.features.shoppingList.components.itemList.ItemShoppingCartComponent
import com.vald3nir.toolkit.compose.components.base.HalfSpaceHeight
import com.vald3nir.toolkit.compose.components.base.ToolkitCard
import com.vald3nir.toolkit.compose.components.base.ToolkitText
import com.vald3nir.toolkit.compose.components.base.halfSpace
import com.vald3nir.toolkit.compose.designSystem.DefaultThemeColors
import com.vald3nir.toolkit.compose.designSystem.schema.ScreenColorSchema

@Composable
fun ItemsListDetailComponent(
    shoppingList: ShoppingListDTO?,
    colors: ScreenColorSchema,
    onClickSeeDetails: (ItemShoppingListDTO) -> Unit = {},
    onUpdateItem: (ItemShoppingListDTO) -> Unit = {},
) {
    if (shoppingList == null) return
    val (itemsAdded, itemsNotAdded) = shoppingList.splitItems()
    Column(modifier = Modifier.background(colors.backgroundColor)) {
        LazyColumn(
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth(), verticalArrangement = Arrangement.spacedBy(halfSpace)
        ) {
            if (itemsNotAdded.isNotEmpty()) {
                item {
                    Column {
                        SectionComponent(
                            text = stringResource(R.string.items_not_added),
                            background = Color.Red,
                        )

//                        ProductCategoryEnum.entries.forEach { category ->
//                            ToolkitCard(
//                                modifier = Modifier
//                                    .fillMaxWidth()
//                                    .clickable { /* ação ao clicar */ },
//                                backgroundColor = colors.itemListBackgroundColor
//                            ) {
//                                ToolkitRow {
//                                    Image(
//                                        painter = painterResource(id = category.icon),
//                                        contentDescription = "nome",
//                                    )
//                                    ToolkitText.Label(text = category.alias, textColor = colors.textColor)
//                                    ToolkitIcon(imageVector = ToolkitIcons.ChevronRight)
//                                }
//                            }
//                            DefaultSpaceHeight()
//                        }
                    }
                }

                itemsIndexed(items = itemsNotAdded, itemContent = { _, item ->
                    ItemShoppingCartComponent(
                        item = item,
                        colors = colors,
                        onClickSeeDetails = onClickSeeDetails,
                        onUpdateItem = onUpdateItem,
                    )
                    HalfSpaceHeight()
                })
            }
            if (itemsAdded.isNotEmpty()) {
                item {
                    SectionComponent(
                        text = stringResource(R.string.items_added),
                        background = Color.Blue,
                    )
                }
                itemsIndexed(items = itemsAdded, itemContent = { _, item ->
                    ItemShoppingCartComponent(
                        item = item,
                        colors = colors,
                        onClickSeeDetails = onClickSeeDetails,
                        onUpdateItem = onUpdateItem,
                    )
                    HalfSpaceHeight()
                })
            }
        }
    }
}

@Composable
private fun SectionComponent(text: String, background: Color) {
    ToolkitCard(backgroundColor = background) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(background),
            verticalArrangement = Arrangement.Center,
        ) {
            HalfSpaceHeight()
            ToolkitText.Title(
                text = text, textColor = Color.White,
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )
            HalfSpaceHeight()
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun Preview() {
    ItemsListDetailComponent(
        colors = DefaultThemeColors().darkColors,
        shoppingList = MockShoppingListDTO.lists().first(),
    )
}