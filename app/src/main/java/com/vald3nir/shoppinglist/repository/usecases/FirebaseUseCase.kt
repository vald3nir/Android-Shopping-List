package com.vald3nir.shoppinglist.repository.usecases

import com.vald3nir.android.firebase.auth.FirebaseAuthenticator
import com.vald3nir.android.firebase.data.FirebaseDB
import com.vald3nir.shoppinglist.BuildConfig
import com.vald3nir.shoppinglist.db.model.entities.ShoppingListModal
import com.vald3nir.shoppinglist.domain.dto.ProductDTO
import com.vald3nir.shoppinglist.domain.mapper.toProductList

object FirebaseUseCase {

    fun saveShoppingLists(lists: List<ShoppingListModal>) {
        val user = FirebaseAuthenticator.getFirebaseUser()
        if (user != null) {
            val key = "debug/shopping_lists/${user.email}"

        }
    }

    fun loadShoppingLists() {

    }

    fun saveProducts() {

    }

    fun loadProductsFromFirebase(onSuccess: (List<ProductDTO>) -> Unit, onError: ((Exception?) -> Unit)?) {
        FirebaseDB.readList(
            path = "/${BuildConfig.FLAVOR}/products",
            onSuccess = { onSuccess(it.toProductList()) },
            onError = onError
        )
    }
}