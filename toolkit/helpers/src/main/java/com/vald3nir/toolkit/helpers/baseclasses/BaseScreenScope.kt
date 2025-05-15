package com.vald3nir.toolkit.helpers.baseclasses

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.navigation.NavController
import com.vald3nir.toolkit.helpers.navigation.UiDestination

open class BaseScreenScope(
    private val viewModel: BaseViewModel? = null,
    private val navController: NavController? = null
) {

    @Composable
    fun CollectUiState(
        onLoading: (Boolean) -> Unit = {},
        onSuccess: (Any?) -> Unit = {},
        onShowMessage: (String) -> Unit = {},
    ) {
        LaunchedEffect(Unit) {
            viewModel?.uiState?.collect { uiEvent ->
                when (uiEvent) {
                    is BaseScreenState.Loading -> onLoading(uiEvent.show)
                    is BaseScreenState.Success -> onSuccess(uiEvent.response)
                    is BaseScreenState.ShowMessage -> uiEvent.message?.let { onShowMessage(it) }
                }
            }
        }
    }

    @Composable
    fun NavigationObserver() {
        viewModel?.navigationEvent?.collectAsState(initial = null)?.value?.let { navigationValue ->
            NavigatorTo(navigationValue)
        }
    }

    @Composable
    private fun NavigatorTo(destination: UiDestination) {
        LaunchedEffect(destination) {
            navController?.navigate(destination)
        }
    }

    fun onBackPressed() {
        navController?.popBackStack()
    }
}