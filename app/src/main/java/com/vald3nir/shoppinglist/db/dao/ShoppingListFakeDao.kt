package com.vald3nir.shoppinglist.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.vald3nir.shoppinglist.db.mock.MockShoppingListModel
import com.vald3nir.shoppinglist.db.model.entities.ItemShoppingListModal
import com.vald3nir.shoppinglist.db.model.entities.ShoppingListModal

@Dao
interface ShoppingListFakeDao {

    // ========================================================================================
    // INSERT FUNCTIONS
    // ========================================================================================

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(entity: ShoppingListModal): Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(entities: List<ItemShoppingListModal>): List<Long>

    // ========================================================================================
    // DELETE FUNCTIONS
    // ========================================================================================

    @Query("DELETE FROM ${ShoppingListModal.TABLE_NAME} WHERE isFakeData = 1")
    suspend fun deleteAllListsFake()

    @Query("DELETE FROM ${ItemShoppingListModal.TABLE_NAME} WHERE isFakeData = 1")
    suspend fun deleteAllItemsFake()

    @Transaction
    suspend fun clearFakeData() {
        deleteAllListsFake()
        deleteAllItemsFake()
    }

    @Transaction
    suspend fun useFakeData() {
        clearFakeData()
        saveShoppingList(MockShoppingListModel.list1, MockShoppingListModel.items1)
        saveShoppingList(MockShoppingListModel.list2, MockShoppingListModel.items2)
        saveShoppingList(MockShoppingListModel.list3, MockShoppingListModel.items3)
    }

    private suspend fun saveShoppingList(list: ShoppingListModal, items: List<ItemShoppingListModal>) {
        val idList = insert(list)
        insert(items.map { it.copy(shoppingListId = idList) })
    }
}