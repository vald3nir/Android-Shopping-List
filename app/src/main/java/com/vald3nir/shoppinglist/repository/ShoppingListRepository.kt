package com.vald3nir.shoppinglist.repository

import com.vald3nir.shoppinglist.db.dao.ProductsDao
import com.vald3nir.shoppinglist.db.dao.ShoppingListDao
import com.vald3nir.shoppinglist.db.dao.ShoppingListFakeDao
import com.vald3nir.shoppinglist.domain.dto.ItemShoppingListDTO
import com.vald3nir.shoppinglist.domain.dto.ProductDTO
import com.vald3nir.shoppinglist.domain.dto.ShoppingListDTO
import com.vald3nir.shoppinglist.domain.mapper.toDTO
import com.vald3nir.shoppinglist.domain.mapper.toDTOList
import com.vald3nir.shoppinglist.domain.mapper.toModal
import com.vald3nir.shoppinglist.domain.mapper.toNewModal
import com.vald3nir.shoppinglist.repository.usecases.FirebaseUseCase
import com.vald3nir.shoppinglist.repository.usecases.importShoppingLists
import com.vald3nir.shoppinglist.repository.usecases.insertOrUpdateShoppingList
import com.vald3nir.shoppinglist.repository.usecases.loadItemShoppingListFlow
import com.vald3nir.shoppinglist.repository.usecases.loadShoppingList
import com.vald3nir.shoppinglist.repository.usecases.loadShoppingListFlow
import com.vald3nir.shoppinglist.repository.usecases.useFakeData
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

interface ShoppingListRepository {
    suspend fun useFakeData()
    suspend fun clearFakeData()
    suspend fun importDatabase()
    suspend fun saveShoppingList(dto: ShoppingListDTO)
    suspend fun cloneShoppingList(shoppingList: ShoppingListDTO?)

    fun getAll(): Flow<List<ShoppingListDTO>>
    fun searchShoppingLists(query: String): Flow<List<ShoppingListDTO>>
    fun loadShoppingListFlow(id: Long?): Flow<ShoppingListDTO?>
    fun loadItemShoppingListFlow(id: Long?): Flow<ItemShoppingListDTO?>

    suspend fun updateShoppingListTitle(shoppingListId: Long, newTitle: String)

    suspend fun updateItemShoppingList(item: ItemShoppingListDTO): ShoppingListDTO?
    suspend fun deleteItemShoppingList(itemId: Long)
    suspend fun deleteShoppingList(id: Long)
    suspend fun addNewItem(newItem: ItemShoppingListDTO)

    fun getAllProducts(): Flow<List<ProductDTO>>
}

class ShoppingListRepositoryImpl @Inject constructor(
    private val dao: ShoppingListDao,
    private val fakeDao: ShoppingListFakeDao,
    private val productsDao: ProductsDao
) : ShoppingListRepository {

    override suspend fun useFakeData() {
        fakeDao.useFakeData()
        productsDao.useFakeData()
    }

    override suspend fun clearFakeData() {
        fakeDao.clearFakeData()
    }

    override suspend fun importDatabase() {
        FirebaseUseCase.importShoppingLists(dao)
    }

    override suspend fun saveShoppingList(dto: ShoppingListDTO) {
        dao.insertOrUpdateShoppingList(dto)
        FirebaseUseCase.exportShoppingLists(dao)
        FirebaseUseCase.importShoppingLists(dao)
    }

    override fun getAll(): Flow<List<ShoppingListDTO>> = dao.importShoppingLists()

    override fun searchShoppingLists(query: String): Flow<List<ShoppingListDTO>> {
        return dao.searchShoppingLists(query).map { it.toDTOList() }
    }

    override fun loadShoppingListFlow(id: Long?) = dao.loadShoppingListFlow(id)

    override fun loadItemShoppingListFlow(id: Long?) = dao.loadItemShoppingListFlow(id)

    override suspend fun updateItemShoppingList(item: ItemShoppingListDTO): ShoppingListDTO? {
        dao.insert(item.toModal())
        return dao.loadShoppingList(item.shoppingListId)
    }

    override suspend fun deleteItemShoppingList(itemId: Long) {
        dao.deleteItemById(itemId)
    }

    override suspend fun deleteShoppingList(id: Long) {
        dao.deleteShoppingListById(id)
    }

    override fun getAllProducts() = productsDao.getAllProducts().toDTO()

    override suspend fun addNewItem(newItem: ItemShoppingListDTO) {
        val modal = newItem.toNewModal()
        dao.insert(modal)
    }

    override suspend fun updateShoppingListTitle(shoppingListId: Long, newTitle: String) {
        dao.updateShoppingListTitle(shoppingListId, newTitle)
    }

    override suspend fun cloneShoppingList(shoppingList: ShoppingListDTO?) {
        shoppingList?.let {
            saveShoppingList(
                it.copy(
                    id = null,
                    title = "Cópia de ${it.title}"
                )
            )
        }
    }
}