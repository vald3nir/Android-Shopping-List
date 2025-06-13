package com.vald3nir.shoppinglist.domain.mapper

import com.vald3nir.shoppinglist.db.model.entities.ItemShoppingListModal
import com.vald3nir.shoppinglist.domain.dto.ItemShoppingListDTO


fun ItemShoppingListModal.toDTO() = ItemShoppingListDTO(
    id = this.id,
    shoppingListId = this.shoppingListId,
    title = this.title,
    quantity = this.quantity,
    unitPrice = this.unitPrice,
    isAdd = this.isAdd
)

fun List<ItemShoppingListModal>.toDTOList() = this.map { it.toDTO() }

fun ItemShoppingListDTO.toNewModal() = ItemShoppingListModal(
    shoppingListId = this.shoppingListId,
    title = this.title,
    quantity = this.quantity,
    unitPrice = this.unitPrice,
    isAdd = this.isAdd
)

fun ItemShoppingListDTO.toModal() = this.toNewModal().copy(id = this.id)
