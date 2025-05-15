package com.vald3nir.toolkit.compose.designSystem

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import com.vald3nir.toolkit.compose.designSystem.schema.ScreenColorSchema

val LocalAppColors = compositionLocalOf<ScreenColorSchema> {
    error("No AppColorScheme provided")
}

@Composable
fun AppTheme(
    themeViewModel: AppThemeViewModel = hiltViewModel(),
    content: @Composable () -> Unit
) {
    val colors = themeViewModel.currentTheme(context = LocalContext.current)
    CompositionLocalProvider(LocalAppColors provides colors) {
        content()
    }
}