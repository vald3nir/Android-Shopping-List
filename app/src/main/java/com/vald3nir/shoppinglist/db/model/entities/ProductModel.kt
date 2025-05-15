package com.vald3nir.shoppinglist.db.model.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = ProductModel.TABLE_NAME)

data class ProductModel(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val name: String? = null,
    val category: String? = null,
) {
    companion object {
        const val TABLE_NAME = "products"
    }
}