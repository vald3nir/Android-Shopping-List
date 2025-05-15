package com.vald3nir.toolkit.helpers.extensions

import androidx.compose.material3.SnackbarHostState
import com.vald3nir.toolkit.helpers.baseclasses.BaseScreenState

suspend fun SnackbarHostState.showMessage(uiEvent: BaseScreenState.ShowMessage) {
    this.showSnackbar(uiEvent.message.orEmpty())
}