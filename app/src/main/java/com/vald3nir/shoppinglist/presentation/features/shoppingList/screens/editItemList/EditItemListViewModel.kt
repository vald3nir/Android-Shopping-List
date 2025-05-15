package com.vald3nir.shoppinglist.presentation.features.shoppingList.screens.editItemList

import com.vald3nir.shoppinglist.domain.dto.ItemShoppingListDTO
import com.vald3nir.shoppinglist.repository.ShoppingListRepository
import com.vald3nir.toolkit.helpers.baseclasses.BaseScreenState
import com.vald3nir.toolkit.helpers.baseclasses.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class EditItemListViewModel @Inject constructor(
    private val repository: ShoppingListRepository
) : BaseViewModel() {

    private var currentItemList: ItemShoppingListDTO? = null
        set(value) {
            field = value
            _itemShoppingList.value = value
        }

    private val _itemShoppingList = MutableStateFlow<ItemShoppingListDTO?>(null)
    val itemShoppingList: StateFlow<ItemShoppingListDTO?> = _itemShoppingList.asStateFlow()

    fun loadItemShoppingList(itemListID: Long?) {
        launchWithScope {
            repository.loadItemShoppingListFlow(itemListID).collect { item ->
                currentItemList = item
            }
        }
    }

    fun updateItem(item: ItemShoppingListDTO?) {
        launchWithScope {
            item?.let { repository.updateItemShoppingList(it) }
            updateViewState(BaseScreenState.Success())
        }
    }

    fun deleteItem(item: ItemShoppingListDTO?) {
        launchWithScope {
            item?.id?.let { repository.deleteItemShoppingList(it) }
            updateViewState(BaseScreenState.Success())
        }
    }
}