package com.vald3nir.shoppinglist.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.vald3nir.shoppinglist.db.model.entities.ProductModel
import kotlinx.coroutines.flow.Flow

@Dao
interface ProductsDao {

    @Query("DELETE FROM ${ProductModel.TABLE_NAME}")
    suspend fun deleteAll()

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(entity: ProductModel): Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(entities: List<ProductModel>): List<Long>

    @Transaction
    suspend fun clearAndInsert(entities: List<ProductModel>) {
        deleteAll()
        insert(entities)
    }

    @Query("SELECT * FROM ${ProductModel.TABLE_NAME} WHERE name LIKE '%' || :name || '%' LIMIT 10")
    suspend fun getProductsByName(name: String): List<ProductModel>

    @Query("SELECT * FROM ${ProductModel.TABLE_NAME}")
    fun getAllProducts(): Flow<List<ProductModel>>

    @Query("SELECT COUNT(*) = 0 FROM ${ProductModel.TABLE_NAME}")
    suspend fun isEmpty(): Boolean

}