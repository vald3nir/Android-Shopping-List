package com.vald3nir.shoppinglist.domain.mapper

import com.vald3nir.shoppinglist.domain.dto.ItemShoppingListDTO
import com.vald3nir.shoppinglist.domain.dto.ShoppingListDTO
import com.vald3nir.shoppinglist.db.model.entities.ItemShoppingListModal
import com.vald3nir.shoppinglist.db.model.entities.ShoppingListModal
import com.vald3nir.shoppinglist.db.model.projections.ShoppingListWithItems


fun ShoppingListWithItems.toShoppingListDTO() = ShoppingListDTO(
    id = this.shoppingList.id,
    title = this.shoppingList.title,
    date = this.shoppingList.date,
    items = this.items.toDTOList()
)

fun ShoppingListModal.toShoppingListDTO() = ShoppingListDTO(
    id = id,
    title = title,
    date = date
)

fun ItemShoppingListModal.toDTO() = ItemShoppingListDTO(
    id = this.id,
    shoppingListId = this.shoppingListId,
    title = this.title,
    quantity = this.quantity,
    unitPrice = this.unitPrice,
    isAdd = this.isAdd
)


fun List<ItemShoppingListModal>.toDTOList() = this.map { it.toDTO() }