package com.vald3nir.shoppinglist.presentation.features.shoppingList.screens.selectType

import com.vald3nir.shoppinglist.repository.ShoppingListRepository
import com.vald3nir.toolkit.helpers.baseclasses.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SelectTypeListScopeViewModel @Inject constructor(private val repository: ShoppingListRepository) : BaseViewModel() {


}