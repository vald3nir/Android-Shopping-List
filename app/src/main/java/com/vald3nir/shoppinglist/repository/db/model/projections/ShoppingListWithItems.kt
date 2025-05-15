package com.vald3nir.shoppinglist.repository.db.model.projections

import androidx.room.Embedded
import androidx.room.Relation
import com.vald3nir.shoppinglist.repository.db.model.entities.ItemShoppingListModal
import com.vald3nir.shoppinglist.repository.db.model.entities.ShoppingListModal

data class ShoppingListWithItems(
    @Embedded val shoppingList: ShoppingListModal,
    @Relation(
        parentColumn = "id",
        entityColumn = "shoppingListId"
    )
    val items: List<ItemShoppingListModal>
)