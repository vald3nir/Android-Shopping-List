package com.vald3nir.shoppinglist.repository.db.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "todos")
data class TodoModel(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val title: String? = null,
    val description: String? = null,
    val isCompleted: Boolean = false
)
