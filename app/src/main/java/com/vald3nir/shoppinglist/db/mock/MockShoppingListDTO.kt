package com.vald3nir.shoppinglist.db.mock

import com.vald3nir.shoppinglist.domain.dto.ItemShoppingListDTO
import com.vald3nir.shoppinglist.domain.dto.ShoppingListDTO


object MockShoppingListDTO {

    val items: List<ItemShoppingListDTO> = listOf(
        ItemShoppingListDTO(id = 0, title = "Arroz", unitPrice = 5.8, quantity = 2, isAdd = true),
        ItemShoppingListDTO(id = 1, title = "Feijão", unitPrice = 4.0, quantity = 0, isAdd = false),
        ItemShoppingListDTO(id = 2, title = "Óleo", unitPrice = 10.0, quantity = 3, isAdd = true),
        ItemShoppingListDTO(id = 3, title = "Açúcar", unitPrice = 4.8, quantity = 5, isAdd = false),
        ItemShoppingListDTO(id = 4, title = "Sal", unitPrice = 0.90, quantity = 1, isAdd = false),
        ItemShoppingListDTO(id = 5, title = "Café", unitPrice = 15.0, quantity = 4, isAdd = true),
        ItemShoppingListDTO(id = 6, title = "Leite", unitPrice = 5.99, quantity = 2, isAdd = false),
        ItemShoppingListDTO(id = 7, title = "Queijo", unitPrice = 10.0, quantity = 3, isAdd = false),
        ItemShoppingListDTO(id = 8, title = "Presunto", unitPrice = 6.0, quantity = 1, isAdd = false),
        ItemShoppingListDTO(id = 9, title = "Frango", unitPrice = 12.0, quantity = 1, isAdd = false),
    )

    fun lists() = listOf(
        ShoppingListDTO(id = 0, title = "Mercado", date = "27/02/2025", items = items),
        ShoppingListDTO(id = 1, title = "Supermercado", date = "27/02/2025", items = items),
        ShoppingListDTO(id = 2, title = "Pão de Açúcar", date = "27/02/2025", items = items),
        ShoppingListDTO(id = 3, title = "Padaria", date = "27/02/2025", items = items),
        ShoppingListDTO(id = 4, title = "Farmácia", date = "27/02/2025", items = items),
        ShoppingListDTO(id = 5, title = "Livraria", date = "27/02/2025", items = items),
        ShoppingListDTO(id = 6, title = "Mercado", date = "27/02/2025", items = items),
        ShoppingListDTO(id = 7, title = "Supermercado", date = "27/02/2025", items = items),
        ShoppingListDTO(id = 8, title = "Pão de Açúcar", date = "27/02/2025", items = items),
        ShoppingListDTO(id = 9, title = "Padaria", date = "27/02/2025", items = items),
        ShoppingListDTO(id = 10, title = "Farmácia", date = "27/02/2025", items = items),
        ShoppingListDTO(id = 11, title = "Livraria", date = "27/02/2025", items = items),
        ShoppingListDTO(id = 12, title = "Livraria 2", date = "27/02/2025", items = items),
    )
}