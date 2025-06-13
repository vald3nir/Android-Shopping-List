package com.vald3nir.shoppinglist.presentation.features.boot

import android.content.Context
import androidx.lifecycle.viewModelScope
import com.vald3nir.android.firebase.auth.FirebaseAuthenticator
import com.vald3nir.android.firebase.data.FirebaseDB
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

    fun checkDatabaseAndRedirect(context: Context, onRedirectToAuth: () -> Unit, onRedirectToHome: () -> Unit) {
        viewModelScope.launch {
            val userLogged = FirebaseAuthenticator.isUserLogged()
            val redirect = if (userLogged) onRedirectToHome else onRedirectToAuth
            if (repository.hasSavedProducts()) {
                return@launch redirect()
            }
            runCatching {
                repository.insertProductsFromDataset(context)
            }.onSuccess {
                redirect()
            }.onFailure {
                it.printStackTrace()
                redirect()
            }
        }
    }
}