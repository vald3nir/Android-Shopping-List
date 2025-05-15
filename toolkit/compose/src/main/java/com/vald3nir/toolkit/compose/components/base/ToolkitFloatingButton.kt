package com.vald3nir.toolkit.compose.components.base

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.FloatingActionButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import com.vald3nir.toolkit.compose.designSystem.DefaultThemeColors
import com.vald3nir.toolkit.compose.designSystem.schema.ScreenColorSchema

@Composable
fun ToolkitFloatingButton(
    containerColor: Color = Color.Black,
    contentColor: Color = Color.White,
    imageVector: ImageVector = ToolkitIcons.Add,
    onClick: () -> Unit = {}
) {
    FloatingActionButton(
        modifier = Modifier.padding(defaultSpace),
        onClick = onClick,
        containerColor = containerColor,
        contentColor = contentColor
    ) {
        ToolkitIcon(
            imageVector = imageVector,
            onClick = onClick,
        )
    }
}

@Preview
@Composable
private fun PreviewLight() {
    ShowContent(DefaultThemeColors().lightColors)
}

@Preview
@Composable
private fun PreviewDark() {
    ShowContent(DefaultThemeColors().darkColors)
}

@Composable
private fun ShowContent(colors: ScreenColorSchema) {
    ToolkitFloatingButton(
        containerColor = colors.backgroundColor,
        contentColor = colors.iconTint
    )
}