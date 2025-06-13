package com.vald3nir.shoppinglist.repository.usecases

import com.vald3nir.shoppinglist.db.dao.ShoppingListDao
import com.vald3nir.shoppinglist.domain.dto.ItemShoppingListDTO
import com.vald3nir.shoppinglist.domain.dto.ShoppingListDTO
import com.vald3nir.shoppinglist.domain.mapper.toDTO
import com.vald3nir.shoppinglist.domain.mapper.toShoppingListDTO
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

fun ShoppingListDao.importShoppingLists(): Flow<List<ShoppingListDTO>> {
    return selectAllShoppingLists().map { it.map { entity -> entity.toShoppingListDTO() } }
}

fun ShoppingListDao.loadShoppingListFlow(id: Long?): Flow<ShoppingListDTO?> {
    return selectShoppingListFlow(id).map { it?.toShoppingListDTO() }
}

fun ShoppingListDao.loadItemShoppingListFlow(id: Long?): Flow<ItemShoppingListDTO?> {
    return selectItemShoppingListFlow(id).map { it?.toDTO() }
}

suspend fun ShoppingListDao.loadShoppingList(id: Long?): ShoppingListDTO? {
    return selectShoppingList(id)?.toShoppingListDTO()
}