package com.vald3nir.shoppinglist.domain.mapper

import com.vald3nir.shoppinglist.db.model.projections.ShoppingListWithItems
import com.vald3nir.shoppinglist.domain.dto.ShoppingListDTO
import com.vald3nir.toolkit.helpers.utils.parses.fromJsonToObject

fun List<String?>.toShoppingListWithItemsModel(): List<ShoppingListWithItems> {
    val list = mutableListOf<ShoppingListWithItems>()
    this.forEach { dataJson ->
        if (!dataJson.isNullOrEmpty()) {
            list.add(fromJsonToObject<ShoppingListWithItems>(dataJson))
        }
    }
    return list
}

fun ShoppingListWithItems.toShoppingListDTO() = ShoppingListDTO(
    id = this.shoppingList.id,
    title = this.shoppingList.title,
    date = this.shoppingList.date,
    items = this.items.toDTOList()
)