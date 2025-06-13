package com.vald3nir.shoppinglist.presentation.features.shoppingList.screens.buildList

import com.vald3nir.shoppinglist.domain.dto.ItemShoppingListDTO
import com.vald3nir.shoppinglist.domain.dto.ShoppingListDTO
import com.vald3nir.shoppinglist.repository.ShoppingListRepository
import com.vald3nir.toolkit.helpers.baseclasses.BaseScreenState
import com.vald3nir.toolkit.helpers.baseclasses.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class BuildListViewModel @Inject constructor(private val repository: ShoppingListRepository) : BaseViewModel() {

    fun saveShoppingList(listAlias: String?, items: MutableList<ItemShoppingListDTO>) {
        launchWithScope {
            repository.saveShoppingList(ShoppingListDTO(title = listAlias, items = items))
            updateViewState(BaseScreenState.Success())
        }
    }
}