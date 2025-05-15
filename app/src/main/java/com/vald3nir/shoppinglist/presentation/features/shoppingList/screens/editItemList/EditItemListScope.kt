package com.vald3nir.shoppinglist.presentation.features.shoppingList.screens.editItemList

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.navigation.NavController
import com.vald3nir.shoppinglist.domain.dto.ItemShoppingListDTO
import com.vald3nir.toolkit.helpers.baseclasses.BaseScreenScope

data class EditItemListScope(
    val viewModel: EditItemListViewModel,
    val navController: NavController
) : BaseScreenScope(viewModel, navController) {

    @Composable
    fun LoadItemShoppingList(itemListID: Long?) {
        LaunchedEffect(Unit) {
            viewModel.loadItemShoppingList(itemListID)
        }
    }

    fun updateItem(item: ItemShoppingListDTO?) {
        viewModel.updateItem(item)
    }

    fun deleteItem(item: ItemShoppingListDTO?) {
        viewModel.deleteItem(item)
    }
}