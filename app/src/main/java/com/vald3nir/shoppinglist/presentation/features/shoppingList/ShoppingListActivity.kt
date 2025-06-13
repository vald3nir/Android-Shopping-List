package com.vald3nir.shoppinglist.presentation.features.shoppingList

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.compose.setContent
import com.vald3nir.shoppinglist.presentation.CustomActivity
import com.vald3nir.shoppinglist.presentation.features.shoppingList.navigation.ShoppingListNavHost
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
internal class ShoppingListActivity : CustomActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ShoppingListNavHost()
        }
    }
}

fun Context.startShoppingListActivity() {
    val intent = Intent(this, ShoppingListActivity::class.java).apply {
        flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
    }
    startActivity(intent)
}