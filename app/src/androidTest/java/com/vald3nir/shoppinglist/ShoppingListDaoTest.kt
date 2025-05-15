package com.vald3nir.shoppinglist

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import com.vald3nir.shoppinglist.repository.db.AppDatabase
import com.vald3nir.shoppinglist.repository.db.dao.ShoppingListDao
import com.vald3nir.shoppinglist.repository.db.model.ItemShoppingListModal
import com.vald3nir.shoppinglist.repository.db.model.entities.ShoppingListModal
import com.vald3nir.toolkit.helpers.utils.getCurrentDate
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

class ShoppingListDaoTest {

    private lateinit var database: AppDatabase
    private lateinit var shoppingListDao: ShoppingListDao

    // Inicializa o banco de dados em memória antes de cada teste
    @Before
    fun setup() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        database = Room.inMemoryDatabaseBuilder(context, AppDatabase::class.java)
            .allowMainThreadQueries().build() // Permite queries na thread principal para fins de teste
        shoppingListDao = database.getShoppingListDao()
    }

    // Limpa os dados do banco após cada teste
    @After
    fun teardown() {
        database.close()
    }

    @Test
    fun insertShoppingList_returnsGeneratedId() = runBlocking {
        val shoppingList = ShoppingListModal(title = "My Shopping List", date = getCurrentDate())
        val shoppingListId = shoppingListDao.insert(shoppingList)
        assertNotEquals(0L, shoppingListId) // Verifica se o id gerado não é 0
    }

    @Test
    fun insertItems_returnsGeneratedIds() = runBlocking {
        val shoppingList = ShoppingListModal(title = "My Shopping List", date = getCurrentDate())
        val shoppingListId = shoppingListDao.insert(shoppingList)
        val items = listOf(
            ItemShoppingListModal(shoppingListId = shoppingListId, title = "Item 1", quantity = 1),
            ItemShoppingListModal(shoppingListId = shoppingListId, title = "Item 2", quantity = 2)
        )
        val itemIds = shoppingListDao.insert(items)
        assertEquals(2, itemIds.size) // Verifica se dois itens foram inseridos
        assertNotEquals(0L, itemIds[0]) // Verifica se o primeiro id gerado não é 0
        assertNotEquals(0L, itemIds[1]) // Verifica se o segundo id gerado não é 0
    }

    @Test
    fun selectShoppingList_returnsCorrectData() = runBlocking {
        val shoppingList = ShoppingListModal(title = "My Shopping List", date = getCurrentDate())
        val shoppingListId = shoppingListDao.insert(shoppingList)
        val items = listOf(
            ItemShoppingListModal(shoppingListId = shoppingListId, title = "Item 1", quantity = 1),
            ItemShoppingListModal(shoppingListId = shoppingListId, title = "Item 2", quantity = 2)
        )
        shoppingListDao.insert(items)

        val shoppingListWithItems = shoppingListDao.selectShoppingList(shoppingListId)
        assertNotNull(shoppingListWithItems)
        assertEquals(shoppingListWithItems?.shoppingList?.title, "My Shopping List")
        assertEquals(shoppingListWithItems?.items?.size, 2) // Verifica se dois itens estão associados
    }

    @Test
    fun deleteShoppingListById_removesList() = runBlocking {
        val shoppingList = ShoppingListModal(title = "My Shopping List", date = getCurrentDate())
        val shoppingListId = shoppingListDao.insert(shoppingList)
        shoppingListDao.deleteShoppingListById(shoppingListId)
        val shoppingListWithItems = shoppingListDao.selectShoppingList(shoppingListId)
        assertNull(shoppingListWithItems) // Verifica que a lista foi removida
    }

    @Test
    fun deleteItemsByShoppingListId_removesItems() = runBlocking {
        val shoppingList = ShoppingListModal(title = "My Shopping List", date = getCurrentDate())
        val shoppingListId = shoppingListDao.insert(shoppingList)

        val items = listOf(
            ItemShoppingListModal(shoppingListId = shoppingListId, title = "Item 1", quantity = 1),
            ItemShoppingListModal(shoppingListId = shoppingListId, title = "Item 2", quantity = 2)
        )
        shoppingListDao.insert(items)

        shoppingListDao.deleteItemsByShoppingListId(shoppingListId)

        val shoppingListWithItems = shoppingListDao.selectShoppingList(shoppingListId)
        assertNotNull(shoppingListWithItems)
        assertEquals(shoppingListWithItems?.items?.size, 0) // Verifica que não há mais itens
    }

    @Test
    fun deleteItemById_removesSingleItem() = runBlocking {
        val shoppingList = ShoppingListModal(title = "My Shopping List", date = getCurrentDate())
        val shoppingListId = shoppingListDao.insert(shoppingList)

        val items = listOf(
            ItemShoppingListModal(shoppingListId = shoppingListId, title = "Item 1", quantity = 1),
            ItemShoppingListModal(shoppingListId = shoppingListId, title = "Item 2", quantity = 2)
        )
        val itemIds = shoppingListDao.insert(items)

        shoppingListDao.deleteItemById(itemIds[0])

        val shoppingListWithItems = shoppingListDao.selectShoppingList(shoppingListId)
        assertNotNull(shoppingListWithItems)
        assertEquals(shoppingListWithItems?.items?.size, 1) // Verifica se apenas 1 item permanece
    }

    @Test
    fun deleteFullShoppingList_removesListAndItems() = runBlocking {
        val shoppingList = ShoppingListModal(title = "My Shopping List", date = getCurrentDate())
        val shoppingListId = shoppingListDao.insert(shoppingList)

        val items = listOf(
            ItemShoppingListModal(shoppingListId = shoppingListId, title = "Item 1", quantity = 1),
            ItemShoppingListModal(shoppingListId = shoppingListId, title = "Item 2", quantity = 2)
        )
        shoppingListDao.insert(items)

        shoppingListDao.deleteShoppingListById(shoppingListId)

        val shoppingListWithItems = shoppingListDao.selectShoppingList(shoppingListId)
        assertNull(shoppingListWithItems) // Verifica que a lista e os itens foram removidos
    }
}
