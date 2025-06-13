package com.vald3nir.shoppinglist.domain.usecases

import com.vald3nir.shoppinglist.BuildConfig
import com.vald3nir.shoppinglist.presentation.features.importData.startImportDataActivity
import com.vald3nir.shoppinglist.presentation.features.shoppingList.startShoppingListActivity
import com.vald3nir.shoppinglist.repository.ShoppingListRepository
import com.vald3nir.toolkit.auth.di.AuthApplicationCall
import com.vald3nir.toolkit.helpers.baseclasses.BaseActivity
import javax.inject.Inject

class AuthApplicationCallImpl @Inject constructor(private val repository: ShoppingListRepository) : AuthApplicationCall {

    override fun getUserClientID() = BuildConfig.SERVER_CLIENT_ID

    override suspend fun useFakeData() {
        repository.useFakeData()
    }

    override suspend fun clearFakeData() {
        repository.clearFakeData()
    }

    override fun finishAuth(currentActivity: BaseActivity) {
        currentActivity.startImportDataActivity {
            currentActivity.startShoppingListActivity()
        }
    }
}