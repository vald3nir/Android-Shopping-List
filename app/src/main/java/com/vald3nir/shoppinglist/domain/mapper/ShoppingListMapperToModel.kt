package com.vald3nir.shoppinglist.domain.mapper

import com.vald3nir.shoppinglist.domain.dto.ItemShoppingListDTO
import com.vald3nir.shoppinglist.domain.dto.ShoppingListDTO
import com.vald3nir.shoppinglist.db.model.entities.ItemShoppingListModal
import com.vald3nir.shoppinglist.db.model.entities.ShoppingListModal


fun List<ShoppingListModal>.toDTOList(): List<ShoppingListDTO> {
    return this.map {
        ShoppingListDTO(
            id = it.id,
            title = it.title,
            date = it.date,
            items = emptyList()
        )
    }
}


fun ItemShoppingListDTO.toNewModal() = ItemShoppingListModal(
    shoppingListId = this.shoppingListId,
    title = this.title,
    quantity = this.quantity,
    unitPrice = this.unitPrice,
    isAdd = this.isAdd
)

fun ItemShoppingListDTO.toModal() = this.toNewModal().copy(id = this.id)
