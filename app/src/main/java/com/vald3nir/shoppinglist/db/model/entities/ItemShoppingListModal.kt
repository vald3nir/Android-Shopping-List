package com.vald3nir.shoppinglist.db.model.entities

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = ItemShoppingListModal.TABLE_NAME,
    foreignKeys = [
        ForeignKey(
            entity = ShoppingListModal::class,
            parentColumns = ["id"],
            childColumns = ["shoppingListId"],
            onDelete = ForeignKey.CASCADE
        ),
    ],
    indices = [Index(value = ["shoppingListId"])]
)
data class ItemShoppingListModal(
    @PrimaryKey(autoGenerate = true)
    val id: Long? = null,
    val shoppingListId: Long? = null, // Foreign key to ShoppingListModal
    val title: String? = null,
    val category: String? = null,
    val quantity: Int = 0,
    val unitPrice: Double = 0.0,
    val isAdd: Boolean = false, // Flag to indicate if the item is added to the shopping list
    val isFakeData: Boolean = false,
) {
    companion object {
        const val TABLE_NAME = "item_shopping_list"
    }
}