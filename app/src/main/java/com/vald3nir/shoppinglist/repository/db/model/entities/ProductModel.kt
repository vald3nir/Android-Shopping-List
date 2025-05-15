package com.vald3nir.shoppinglist.repository.db.model.entities

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = ProductModel.TABLE_NAME,
    foreignKeys = [
        ForeignKey(
            entity = ProductBrandsModel::class,
            parentColumns = ["id"],
            childColumns = ["brandId"],
            onDelete = ForeignKey.CASCADE
        )
    ],
    indices = [Index(value = ["brandId"])]
)

data class ProductModel(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val name: String? = null,
    val category: String? = null,
    val brandId: Long? = null // Foreign key to ProductBrandsModel
) {
    companion object {
        const val TABLE_NAME = "products"
    }
}