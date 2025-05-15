package com.vald3nir.shoppinglist.presentation.features.shoppingList.screens.listDetail

import com.vald3nir.shoppinglist.domain.dto.ItemShoppingListDTO
import com.vald3nir.shoppinglist.domain.dto.ShoppingListDTO
import com.vald3nir.shoppinglist.domain.usecases.sort
import com.vald3nir.shoppinglist.repository.ShoppingListRepository
import com.vald3nir.toolkit.helpers.baseclasses.BaseScreenState
import com.vald3nir.toolkit.helpers.baseclasses.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class ListDetailViewModel @Inject constructor(
    private val repository: ShoppingListRepository
) : BaseViewModel() {

    private var currentList: ShoppingListDTO? = null
        set(value) {
            field = value
            _shoppingList.value = value
        }

    private val _shoppingList = MutableStateFlow<ShoppingListDTO?>(null)
    val shoppingList: StateFlow<ShoppingListDTO?> = _shoppingList.asStateFlow()

    fun loadShoppingList(shoppingListId: Long?) {
        launchWithScope {
            repository.loadShoppingListFlow(shoppingListId).collect { list ->
                currentList = list?.sort()
            }
        }
    }

    fun updateShoppingListItem(item: ItemShoppingListDTO) {
        launchWithScope {
            currentList = repository.updateItemShoppingList(item)?.sort()
        }
    }

    fun addNewItem(shoppingListId: Long, newItem: ItemShoppingListDTO) {
        launchWithScope {
            repository.addNewItem(newItem.copy(shoppingListId = shoppingListId))
            loadShoppingList(shoppingListId)
        }
    }

    fun updateShoppingListTitle(shoppingListId: Long, newTitle: String) {
        launchWithScope {
            repository.updateShoppingListTitle(shoppingListId = shoppingListId, newTitle = newTitle)
            loadShoppingList(shoppingListId)
        }
    }

    fun cloneShoppingList(shoppingList: ShoppingListDTO?) {
        launchWithScope {
            repository.cloneShoppingList(shoppingList)
            updateViewState(BaseScreenState.Success())
        }
    }


    fun deleteShoppingList(shoppingListId: Long) {
        launchWithScope {
            repository.deleteShoppingList(shoppingListId)
        }
    }
}