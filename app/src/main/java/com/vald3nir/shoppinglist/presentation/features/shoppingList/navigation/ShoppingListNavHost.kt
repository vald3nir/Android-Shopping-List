package com.vald3nir.shoppinglist.presentation.features.shoppingList.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.vald3nir.shoppinglist.presentation.features.shoppingList.screens.editItemList.EditItemListScreen
import com.vald3nir.shoppinglist.presentation.features.shoppingList.screens.home.HomeScreen
import com.vald3nir.shoppinglist.presentation.features.shoppingList.screens.home.HomeScreenScope
import com.vald3nir.shoppinglist.presentation.features.shoppingList.screens.home.HomeViewModel
import com.vald3nir.shoppinglist.presentation.features.shoppingList.screens.buildList.BuildListScope
import com.vald3nir.shoppinglist.presentation.features.shoppingList.screens.buildList.BuildListScreen
import com.vald3nir.shoppinglist.presentation.features.shoppingList.screens.buildList.BuildListViewModel
import com.vald3nir.shoppinglist.presentation.features.shoppingList.screens.editItemList.EditItemListScope
import com.vald3nir.shoppinglist.presentation.features.shoppingList.screens.editItemList.EditItemListViewModel
import com.vald3nir.shoppinglist.presentation.features.shoppingList.screens.listDetail.ListDetailScope
import com.vald3nir.shoppinglist.presentation.features.shoppingList.screens.listDetail.ListDetailScreen
import com.vald3nir.shoppinglist.presentation.features.shoppingList.screens.listDetail.ListDetailViewModel

@Composable
fun ShoppingListNavHost() {
    val navController = rememberNavController()
    NavHost(navController, startDestination = ShoppingListScreenRoute.Home) {

        composable<ShoppingListScreenRoute.Home> {
            HomeScreenScope(
                viewModel = hiltViewModel<HomeViewModel>(),
                navController = navController
            ).HomeScreen()
        }

        composable<ShoppingListScreenRoute.NewList> {
            BuildListScope(
                viewModel = hiltViewModel<BuildListViewModel>(),
                navController = navController
            ).BuildListScreen()
        }

        composable<ShoppingListScreenRoute.ShowList> { backStackEntry ->
            val router: ShoppingListScreenRoute.ShowList = backStackEntry.toRoute<ShoppingListScreenRoute.ShowList>()
            ListDetailScope(
                viewModel = hiltViewModel<ListDetailViewModel>(),
                navController = navController
            ).ListDetailScreen(shoppingListID = router.shoppingListID)
        }

        composable<ShoppingListScreenRoute.EditItemList> { backStackEntry ->
            val router: ShoppingListScreenRoute.EditItemList = backStackEntry.toRoute<ShoppingListScreenRoute.EditItemList>()
            EditItemListScope(
                viewModel = hiltViewModel<EditItemListViewModel>(),
                navController = navController
            ).EditItemListScreen(itemListID = router.itemListID)
        }
    }
}