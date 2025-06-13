package com.vald3nir.shoppinglist.domain.mapper

import com.vald3nir.shoppinglist.db.model.entities.ShoppingListModal
import com.vald3nir.shoppinglist.domain.dto.ShoppingListDTO


fun ShoppingListModal.toShoppingListDTO() = ShoppingListDTO(
    id = id,
    title = title,
    date = date
)

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