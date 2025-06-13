package com.vald3nir.shoppinglist.presentation.features.importData

import androidx.lifecycle.viewModelScope
import com.vald3nir.android.firebase.auth.FirebaseAuthenticator
import com.vald3nir.shoppinglist.repository.ProductsRepository
import com.vald3nir.shoppinglist.repository.ShoppingListRepository
import com.vald3nir.toolkit.helpers.baseclasses.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
internal class ImportDataViewModel @Inject constructor(
    private val productsRepository: ProductsRepository,
    private val shoppingListRepository: ShoppingListRepository
) : BaseViewModel() {

    fun importDatabaseAndRedirect(onRedirectToAuth: () -> Unit, onFinished: () -> Unit) {
        viewModelScope.launch {
            val userLogged = FirebaseAuthenticator.isUserLogged()
            kotlin.runCatching {
                if (!userLogged) {
                    onRedirectToAuth()
                    return@launch
                }
                productsRepository.importDatabase()
                shoppingListRepository.importDatabase()
                onFinished()
            }.onFailure {
                if (userLogged) onFinished() else onRedirectToAuth()
            }
        }
    }
}