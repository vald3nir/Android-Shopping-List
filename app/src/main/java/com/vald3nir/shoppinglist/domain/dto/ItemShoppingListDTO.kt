package com.vald3nir.shoppinglist.domain.dto

import com.vald3nir.toolkit.helpers.utils.formatCurrency
import kotlinx.serialization.Serializable
import java.util.UUID

@Serializable
data class ItemShoppingListDTO(
    val uuid: String = UUID.randomUUID().toString(),
    val id: Long? = null,
    val shoppingListId: Long? = null,
    val title: String? = null,
    val quantity: Int = 0,
    val unitPrice: Double = 0.0,
    var isAdd: Boolean = false
) {
    val totalValue: Double
        get() = unitPrice * quantity

    val description: String
        get() = unitPrice.formatCurrency() + " x " + quantity.toString() + " Uni."

    val quantityDescription: String
        get() = title.orEmpty() + " - " + quantity.toString() + " Uni."

    val hasWarning: Boolean = quantity <= 0 || unitPrice <= 0
}