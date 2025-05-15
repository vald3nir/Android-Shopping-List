package com.vald3nir.toolkit.compose.designSystem

import androidx.compose.ui.graphics.Color
import com.vald3nir.toolkit.compose.designSystem.schema.ScreenColorSchema


data class DefaultThemeColors(
    val lightColors: ScreenColorSchema = ScreenColorSchema(
        // Colors for main screens
        backgroundColor = Color.White,
        textColor = Color.Black,
        linkColor = Color.Blue,
        iconTint = Color.Blue,

        // Colors for buttons
        buttonBackgroundColor = Color.Blue,
        buttonTextColor = Color.White,

        // Colors for toolbars
        toolbarBackgroundColor = Color(0xFFF6EEEE),
        toolbarTextColor = Color.Black,
        toolbarIconTint = Color.Black,

        // Colors for boxes, menus or highlighted components
        highlightedBackgroundColor = Color.Blue,
        highlightedTextColor = Color.White,
        highlightedIconTint = Color.White,

        // Colors for item in a list
        itemListBackgroundColor = Color(0xFFE5DFDF),
        itemListTextColor = Color.Black,
        itemListIconTint = Color.Black,

        // Colors for checkbox components
        checkedColor = Color(0xFF2E2EE8),
        checkmarkColor = Color.White,
        uncheckedColor = Color(0xFF2E2EE8),
        disabledColor = Color.Gray,

        // Colors for Dialogs
        dialogBackgroundColor = Color.White,
        dialogTextColor = Color.Black,
        dialogConfirmBtnTextColor = Color.White,
        dialogConfirmBtnBackgroundColor = Color.Blue,
        dialogCancelBtnTextColor = Color.Black,
        dialogCancelBtnBackgroundColor = Color.White,

        // Colors for BottomSheet
        bottomSheetBackgroundColor = Color.LightGray,
        bottomSheetTextColor = Color.Black,
        bottomSheetIconTint = Color.Black,

        // Colors for Switch
        switchEnableColor = Color.Blue,
        switchDisableColor = Color.Gray,
    ),
    val darkColors: ScreenColorSchema = ScreenColorSchema(
        // Colors for main screens
        backgroundColor = Color.Black,
        textColor = Color.White,
        linkColor = Color.Yellow,
        iconTint = Color.White,

        // Colors for buttons
        buttonBackgroundColor = Color.White,
        buttonTextColor = Color.Black,

        // Colors for toolbars
        toolbarBackgroundColor = Color.DarkGray,
        toolbarTextColor = Color.White,
        toolbarIconTint = Color.White,

        // Colors for boxes, menus or highlighted components
        highlightedBackgroundColor = Color(0xFF363232),
        highlightedTextColor = Color.White,
        highlightedIconTint = Color.White,

        // Colors for item in a list
        itemListBackgroundColor = Color.DarkGray,
        itemListTextColor = Color.White,
        itemListIconTint = Color.White,

        // Colors for checkbox components
        checkedColor = Color.White,
        checkmarkColor = Color.Black,
        uncheckedColor = Color.White,
        disabledColor = Color.Gray,

        // Colors for Dialogs
        dialogBackgroundColor = Color.DarkGray,
        dialogTextColor = Color.White,
        dialogConfirmBtnTextColor = Color.White,
        dialogConfirmBtnBackgroundColor = Color.Gray,
        dialogCancelBtnTextColor = Color.White,
        dialogCancelBtnBackgroundColor = Color.DarkGray,

        // Colors for BottomSheet
        bottomSheetBackgroundColor = Color.DarkGray,
        bottomSheetTextColor = Color.White,
        bottomSheetIconTint = Color.White,

        // Colors for Switch
        switchEnableColor = Color.Yellow,
        switchDisableColor = Color.LightGray,
    )
)