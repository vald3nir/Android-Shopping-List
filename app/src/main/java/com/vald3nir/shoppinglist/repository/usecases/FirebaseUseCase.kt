package com.vald3nir.shoppinglist.repository.usecases


import com.vald3nir.android.firebase.auth.FirebaseAuthenticator
import com.vald3nir.android.firebase.data.FirebaseDB
import com.vald3nir.android.firebase.utils.parseEmailToKey
import com.vald3nir.shoppinglist.BuildConfig
import com.vald3nir.shoppinglist.db.dao.ProductsDao
import com.vald3nir.shoppinglist.db.dao.ShoppingListDao
import com.vald3nir.shoppinglist.domain.mapper.toProductsModel
import com.vald3nir.shoppinglist.domain.mapper.toShoppingListWithItemsModel

object FirebaseUseCase {

    private fun getKey() = FirebaseAuthenticator.getFirebaseUser()?.email?.parseEmailToKey()

    suspend fun importShoppingLists(dao: ShoppingListDao) {
        getKey()?.let { key ->
            val response = FirebaseDB.readList(path = "/${BuildConfig.FLAVOR}/clients/$key/shopping_lists").toShoppingListWithItemsModel()
            dao.cleanAndInsert(response)
        }
    }

    suspend fun exportShoppingLists(dao: ShoppingListDao) {
        getKey()?.let { key ->
            val shoppingLists = dao.loadAllListsWithItems()
            FirebaseDB.insertOrUpdate(path = "/${BuildConfig.FLAVOR}/clients/$key/shopping_lists", data = shoppingLists)
        }
    }

    suspend fun importProducts(dao: ProductsDao) {
        val response = FirebaseDB.readList(path = "/${BuildConfig.FLAVOR}/products").toProductsModel()
        dao.clearAndInsert(response)
    }
}