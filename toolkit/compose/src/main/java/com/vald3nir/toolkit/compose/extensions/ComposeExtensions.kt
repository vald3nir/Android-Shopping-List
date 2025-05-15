package com.vald3nir.toolkit.compose.extensions

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import com.vald3nir.toolkit.compose.components.base.ToolkitText


@Composable
fun String.BuildLabel(textColor: Color): @Composable () -> Unit = {
    ToolkitText.Label(text = this, textColor = textColor)
}