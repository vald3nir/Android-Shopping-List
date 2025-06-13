package com.vald3nir.shoppinglist.repository.usecases

import com.vald3nir.shoppinglist.db.dao.ShoppingListDao
import com.vald3nir.shoppinglist.db.model.entities.ItemShoppingListModal
import com.vald3nir.shoppinglist.db.model.entities.ShoppingListModal
import com.vald3nir.shoppinglist.domain.dto.ShoppingListDTO
import com.vald3nir.toolkit.helpers.utils.orZero

suspend fun ShoppingListDao.insertOrUpdateShoppingList(dto: ShoppingListDTO) {
    val shoppingListId = updateShoppingListModel(dto)
    updateOrInsertItemShoppingList(dto.copy(id = shoppingListId))
}

private suspend fun ShoppingListDao.updateShoppingListModel(dto: ShoppingListDTO): Long {
    val existentEntity = selectShoppingList(dto.id)?.shoppingList
    val model = existentEntity?.copy(
        id = dto.id.orZero(),
        title = dto.title,
    ) ?: ShoppingListModal(
        title = dto.title
    )
    return insert(model)
}

private suspend fun ShoppingListDao.updateOrInsertItemShoppingList(dto: ShoppingListDTO) {
    val newItems: List<ItemShoppingListModal> = dto.items.map {
        ItemShoppingListModal(
            shoppingListId = dto.id,
            title = it.title,
            unitPrice = it.unitPrice,
            quantity = it.quantity,
            isAdd = it.isAdd
        )
    }
    insert(newItems)
}