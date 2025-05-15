package com.vald3nir.shoppinglist.presentation.features.shoppingList.screens.home

import androidx.lifecycle.viewModelScope
import com.vald3nir.android.firebase.auth.FirebaseAuthenticator
import com.vald3nir.shoppinglist.repository.ShoppingListRepository
import com.vald3nir.toolkit.helpers.baseclasses.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val repository: ShoppingListRepository
) : BaseViewModel() {

    fun getUserLogged() = FirebaseAuthenticator.getFirebaseUser()
    fun isUserLogged() = FirebaseAuthenticator.isUserLogged()
    fun userLogout()  { FirebaseAuthenticator.disconnect() }

    private val _searchQuery = MutableStateFlow("")
    val searchQuery = _searchQuery.asStateFlow()

    val filteredList = searchQuery.flatMapLatest { query ->
        if (query.isBlank()) {
            repository.getAll()
        } else {
            repository.searchShoppingLists(query)
        }
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = emptyList()
    )

    fun updateSearchQuery(query: String) {
        _searchQuery.value = query
    }
}