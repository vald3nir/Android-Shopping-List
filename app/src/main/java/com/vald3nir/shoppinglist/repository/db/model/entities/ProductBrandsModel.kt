package com.vald3nir.shoppinglist.repository.db.model.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = ProductBrandsModel.TABLE_NAME)
data class ProductBrandsModel(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val name: String? = null,
) {
    companion object {
        const val TABLE_NAME = "products_brands"
    }
}