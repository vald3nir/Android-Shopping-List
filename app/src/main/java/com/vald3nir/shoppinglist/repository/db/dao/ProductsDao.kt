package com.vald3nir.shoppinglist.repository.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.vald3nir.shoppinglist.repository.db.model.entities.ItemShoppingListModal
import com.vald3nir.shoppinglist.repository.db.model.entities.ProductModel
import com.vald3nir.shoppinglist.repository.db.model.projections.ProductWithBrand
import kotlinx.coroutines.flow.Flow

@Dao
interface ProductsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(entity: ProductModel): Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(entities: List<ProductModel>): List<Long>

    @Query("SELECT * FROM ${ProductModel.TABLE_NAME} WHERE id = :id")
    suspend fun getProduct(id: Long?): ProductModel?

    @Query("SELECT * FROM ${ProductModel.TABLE_NAME}")
    fun getProducts(): Flow<List<ProductModel>>

    @Query("DELETE FROM ${ProductModel.TABLE_NAME}")
    suspend fun deleteAll()

    @Transaction
    @Query(
        """
        SELECT * FROM ${ItemShoppingListModal.TABLE_NAME} AS item
        LEFT JOIN products AS product ON item.productId = product.id
        LEFT JOIN products_brands AS brand ON product.brandId = brand.id
        """
    )
    suspend fun getItemsWithProductsAndBrands(): List<ProductWithBrand>
}