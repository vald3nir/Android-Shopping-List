package com.vald3nir.shoppinglist.presentation.features.shoppingList.screens.listDetail

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.navigation.NavController
import com.vald3nir.shoppinglist.domain.dto.ItemShoppingListDTO
import com.vald3nir.shoppinglist.domain.dto.ShoppingListDTO
import com.vald3nir.shoppinglist.presentation.features.shoppingList.navigation.ShoppingListScreenRoute
import com.vald3nir.toolkit.helpers.baseclasses.BaseScreenScope

data class ListDetailScope(
    val viewModel: ListDetailViewModel,
    val navController: NavController? = null,
) : BaseScreenScope(viewModel, navController) {

    @Composable
    fun LoadShoppingList(shoppingListID: Long?) {
        LaunchedEffect(Unit) {
            viewModel.loadShoppingList(shoppingListID)
        }
    }

    var shoppingListID: Long? = null

    val onUpdateItem: (ItemShoppingListDTO) -> Unit = {
        viewModel.updateShoppingListItem(it)
    }

    val onAddNewItem: (ItemShoppingListDTO) -> Unit = { newItem ->
        shoppingListID?.let {
            viewModel.addNewItem(
                shoppingListId = it,
                newItem = newItem
            )
        }
    }

    val onEditListName: (String) -> Unit = { newTitle ->
        shoppingListID?.let {
            viewModel.updateShoppingListTitle(
                shoppingListId = it,
                newTitle = newTitle
            )
        }
    }

    fun cloneList(shoppingList: ShoppingListDTO?) {
        viewModel.cloneShoppingList(shoppingList)
    }

    fun deleteList() {
        shoppingListID?.let {
            viewModel.deleteShoppingList(shoppingListId = it)
            onBackPressed()
        }
    }

    val onClickEditItem: (ItemShoppingListDTO) -> Unit = { item ->
        navController?.navigate(ShoppingListScreenRoute.EditItemList(item.id))
    }
}