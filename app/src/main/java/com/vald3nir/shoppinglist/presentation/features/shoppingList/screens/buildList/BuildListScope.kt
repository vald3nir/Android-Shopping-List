package com.vald3nir.shoppinglist.presentation.features.shoppingList.screens.buildList

import androidx.navigation.NavController
import com.vald3nir.shoppinglist.domain.dto.ItemShoppingListDTO
import com.vald3nir.toolkit.helpers.baseclasses.BaseScreenScope

data class BuildListScope(
    val viewModel: BuildListViewModel,
    val navController: NavController? = null,
) : BaseScreenScope(viewModel, navController) {


    fun MutableList<ItemShoppingListDTO>.saveList(listAlias: String?) {
        viewModel.saveShoppingList(listAlias = listAlias, items = this)
    }

    fun MutableList<ItemShoppingListDTO>.addItem(item: ItemShoppingListDTO) {
        val index = indexOfFirst { it.uuid == item.uuid }
        if (index >= 0) this[index] = item else add(item)
    }

}