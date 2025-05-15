package com.vald3nir.shoppinglist.presentation.features.shoppingList.navigation

import com.vald3nir.toolkit.helpers.navigation.UiDestination
import kotlinx.serialization.Serializable

sealed interface ShoppingListScreenRoute : UiDestination {
    @Serializable
    data object Home : ShoppingListScreenRoute

    @Serializable
    data object NewList : ShoppingListScreenRoute

    @Serializable
    data class ShowList(val shoppingListID: Long?) : ShoppingListScreenRoute

    @Serializable
    data class EditItemList(val itemListID: Long?) : ShoppingListScreenRoute
}