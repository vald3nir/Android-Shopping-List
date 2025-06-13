package com.vald3nir.shoppinglist.domain.usecases

import com.vald3nir.shoppinglist.domain.dto.ItemShoppingListDTO
import com.vald3nir.shoppinglist.domain.dto.ShoppingListDTO
import com.vald3nir.toolkit.helpers.utils.formatCurrency
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

fun ShoppingListDTO.sort(): ShoppingListDTO {
    val sortedItems = items.sortedBy { it.isAdd }
    return copy(items = sortedItems)
}

fun List<ItemShoppingListDTO>.totalValue(): String {
    var value = 0.0
    forEach { if (it.isAdd) value += (it.unitPrice * it.quantity) }
    return value.formatCurrency()
}

fun ShoppingListDTO?.splitItems(): Pair<List<ItemShoppingListDTO>, List<ItemShoppingListDTO>> {
    """
        Returns the list divided between items added and not added to the shopping cart
    """.trimIndent()
    var itemsAdded = emptyList<ItemShoppingListDTO>()
    var itemsNotAdded = emptyList<ItemShoppingListDTO>()
    this?.items.orEmpty().forEach {
        if (it.isAdd) itemsAdded = itemsAdded.plus(it)
        else itemsNotAdded = itemsNotAdded.plus(it)
    }
    return Pair(itemsAdded, itemsNotAdded)
}

fun String?.titleListFormated(): String {
    return if (isNullOrEmpty()) {
        val currentDate = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault()).format(Date())
        "Lista de Compras $currentDate"
    } else this
}