package com.vald3nir.shoppinglist.presentation.features.boot

import android.content.Context
import androidx.lifecycle.viewModelScope
import com.vald3nir.android.firebase.auth.FirebaseAuthenticator
import com.vald3nir.android.firebase.data.FirebaseDB
import com.vald3nir.shoppinglist.domain.dto.ProductDTO
import com.vald3nir.shoppinglist.repository.ProductsRepository
import com.vald3nir.toolkit.helpers.baseclasses.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
internal class BootViewModel @Inject constructor(private val repository: ProductsRepository) : BaseViewModel() {

    fun setupFirebaseDB() {
        FirebaseDB.enableOfflineMode()
    }

    fun checkFirebaseDB(context: Context, onRedirectToAuth: () -> Unit, onRedirectToHome: () -> Unit) {
        viewModelScope.launch {
            val userLogged = FirebaseAuthenticator.isUserLogged()
            if (repository.hasSavedProducts()) {
                return@launch if (userLogged) onRedirectToHome() else onRedirectToAuth()
            }
            if (userLogged) {
                repository.loadProductsFromFirebase(
                    onSuccess = {
                        insertProductsFromFirebase(it, onRedirectToHome)
                    },
                    onError = {
                        it?.printStackTrace()
                        onRedirectToHome()
                    }
                )
            } else {
                insertProductsFromDataset(context, onRedirectToAuth)
            }
        }
    }

    private fun insertProductsFromFirebase(products: List<ProductDTO>, onRedirectToHome: () -> Unit) {
        viewModelScope.launch {
            repository.insertProducts(products)
            onRedirectToHome()
        }
    }

    private suspend fun insertProductsFromDataset(context: Context, onRedirectToAuth: () -> Unit) {
        runCatching {
            repository.insertProductsFromDataset(context)
        }.onSuccess {
            onRedirectToAuth()
        }.onFailure {
            it.printStackTrace()
            onRedirectToAuth()
        }
    }
}