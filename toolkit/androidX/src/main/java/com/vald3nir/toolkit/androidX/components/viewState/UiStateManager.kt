package com.vald3nir.toolkit.androidX.components.viewState

interface UiStateManager {
    suspend fun updateUiState(state: UiState)
}

sealed class UiState {
    data class Loading(val show: Boolean) : UiState()
    data class Message(val msg: String) : UiState()
    data class Error(val e: Exception) : UiState()
}