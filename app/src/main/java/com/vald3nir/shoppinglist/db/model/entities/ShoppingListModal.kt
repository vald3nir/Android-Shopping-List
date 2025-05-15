package com.vald3nir.shoppinglist.db.model.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.vald3nir.toolkit.helpers.utils.getCurrentDate

@Entity(tableName = ShoppingListModal.TABLE_NAME)
data class ShoppingListModal(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val title: String? = null,
    val date: String = getCurrentDate(),
    val lastUpdated: Long = System.currentTimeMillis(),
    val isFakeData: Boolean = false,
) {
    companion object {
        const val TABLE_NAME = "shopping_list"
    }
}