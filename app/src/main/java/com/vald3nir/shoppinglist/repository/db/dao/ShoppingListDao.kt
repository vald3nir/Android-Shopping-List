package com.vald3nir.shoppinglist.repository.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.vald3nir.shoppinglist.repository.db.model.entities.ItemShoppingListModal
import com.vald3nir.shoppinglist.repository.db.model.entities.ShoppingListModal
import com.vald3nir.shoppinglist.repository.db.model.projections.ShoppingListWithItems
import kotlinx.coroutines.flow.Flow

@Dao
interface ShoppingListDao {

    // ========================================================================================
    // INSERT FUNCTIONS
    // ========================================================================================

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(entity: ShoppingListModal): Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(entities: List<ItemShoppingListModal>): List<Long>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(entity: ItemShoppingListModal): Long

    // ========================================================================================
    // UPDATE FUNCTIONS
    // ========================================================================================

    @Query("UPDATE ${ShoppingListModal.TABLE_NAME} SET title = :newTitle WHERE id = :shoppingListId")
    suspend fun updateShoppingListTitle(shoppingListId: Long, newTitle: String)

    // ========================================================================================
    // DELETE FUNCTIONS
    // ========================================================================================

    @Query("DELETE FROM ${ShoppingListModal.TABLE_NAME} WHERE id = :shoppingListId")
    suspend fun deleteShoppingListById(shoppingListId: Long)

    @Query("DELETE FROM ${ItemShoppingListModal.TABLE_NAME} WHERE shoppingListId = :shoppingListId")
    suspend fun deleteItemsByShoppingListId(shoppingListId: Long)

    @Query("DELETE FROM ${ItemShoppingListModal.TABLE_NAME} WHERE id = :itemId")
    suspend fun deleteItemById(itemId: Long)


    // ========================================================================================
    // QUERY FUNCTIONS
    // ========================================================================================

    @Transaction
    @Query("SELECT * FROM ${ShoppingListModal.TABLE_NAME} WHERE id = :shoppingListId")
    fun selectShoppingListFlow(shoppingListId: Long?): Flow<ShoppingListWithItems?>

    @Query("SELECt * FROM ${ItemShoppingListModal.TABLE_NAME} WHERE id = :itemId")
    fun selectItemShoppingListFlow(itemId: Long?): Flow<ItemShoppingListModal?>

    @Transaction
    @Query("SELECT * FROM ${ShoppingListModal.TABLE_NAME} WHERE id = :shoppingListId")
    suspend fun selectShoppingList(shoppingListId: Long?): ShoppingListWithItems?

    @Query("SELECT * FROM ${ShoppingListModal.TABLE_NAME} ORDER BY lastUpdated DESC")
    fun selectAllShoppingLists(): Flow<List<ShoppingListModal>>

    @Query(
        """
            SELECT * FROM ${ShoppingListModal.TABLE_NAME}
            WHERE title LIKE '%' || :query || '%' OR date LIKE '%' || :query || '%'
        """
    )
    fun searchShoppingLists(query: String): Flow<List<ShoppingListModal>>
}
