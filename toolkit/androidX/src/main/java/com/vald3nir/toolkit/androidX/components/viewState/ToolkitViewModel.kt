package com.vald3nir.toolkit.androidX.components.viewState

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

open class ToolkitViewModel : ViewModel() {

    protected fun <T> launchJob(
        uiStateManager: UiStateManager,
        action: suspend () -> T,
        messageSuccess: String? = null
    ): Job {
        return viewModelScope.launch {
            uiStateManager.updateUiState(UiState.Loading(show = true))
            try {
                action()
                messageSuccess?.let {
                    uiStateManager.updateUiState(UiState.Message(it))
                }
            } catch (e: Exception) {
                uiStateManager.updateUiState(UiState.Error(e))
            } finally {
                uiStateManager.updateUiState(UiState.Loading(show = false))
            }
        }
    }
}