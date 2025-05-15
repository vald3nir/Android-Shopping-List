package com.vald3nir.shoppinglist.db.mock

import com.vald3nir.shoppinglist.db.model.entities.ItemShoppingListModal
import com.vald3nir.shoppinglist.db.model.entities.ShoppingListModal
import com.vald3nir.toolkit.helpers.utils.getCurrentDate

object MockShoppingListModel {

    val list1 = ShoppingListModal(title = "Supermercado", date = getCurrentDate(), isFakeData = true)
    val list2 = ShoppingListModal(title = "Frigorífico", date = getCurrentDate(), isFakeData = true)
    val list3 = ShoppingListModal(title = "Feira", date = getCurrentDate(), isFakeData = true)

    val items1: List<ItemShoppingListModal> = listOf(
        ItemShoppingListModal(title = "Feijão", unitPrice = 5.0, quantity = 1, isAdd = false, isFakeData = true),
        ItemShoppingListModal(title = "Óleo", unitPrice = 10.0, quantity = 1, isAdd = false, isFakeData = true),
        ItemShoppingListModal(title = "Açúcar", unitPrice = 3.0, quantity = 4, isAdd = false, isFakeData = true),
        ItemShoppingListModal(title = "Sal", unitPrice = 0.90, quantity = 1, isAdd = false, isFakeData = true),
        ItemShoppingListModal(title = "Café", unitPrice = 15.0, quantity = 5, isAdd = true, isFakeData = true),
        ItemShoppingListModal(title = "Leite", unitPrice = 5.99, quantity = 1, isAdd = false, isFakeData = true),
        ItemShoppingListModal(title = "Arroz", unitPrice = 3.5, quantity = 2, isAdd = true, isFakeData = true),
    )
    val items2: List<ItemShoppingListModal> = listOf(
        ItemShoppingListModal(title = "Picanha", unitPrice = 100.0, quantity = 2, isAdd = true, isFakeData = true),
        ItemShoppingListModal(title = "Frango", unitPrice = 20.0, quantity = 1, isAdd = false, isFakeData = true),
        ItemShoppingListModal(title = "Linguiça", unitPrice = 15.0, quantity = 1, isAdd = false, isFakeData = true),
        ItemShoppingListModal(title = "Carne Moida", unitPrice = 25.0, quantity = 4, isAdd = false, isFakeData = true)
    )
    val items3: List<ItemShoppingListModal> = listOf(
        ItemShoppingListModal(title = "Banana", unitPrice = 2.0, quantity = 6, isAdd = true, isFakeData = true),
        ItemShoppingListModal(title = "Maçã", unitPrice = 2.5, quantity = 3, isAdd = true, isFakeData = true),
        ItemShoppingListModal(title = "Laranja", unitPrice = 3.0, quantity = 2, isAdd = true, isFakeData = true)
    )
}