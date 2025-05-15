package com.vald3nir.toolkit.compose.designSystem.schema

import androidx.compose.ui.graphics.Color

data class ScreenColorSchema(
    // Colors for main screens
    val backgroundColor: Color,
    val textColor: Color,
    val linkColor: Color,
    val iconTint: Color,

    // Colors for buttons
    val buttonBackgroundColor: Color,
    val buttonTextColor: Color,

    // Colors for toolbars
    val toolbarBackgroundColor: Color,
    val toolbarTextColor: Color,
    val toolbarIconTint: Color,

    // Colors for boxes, menus or highlighted components
    val highlightedBackgroundColor: Color,
    val highlightedTextColor: Color,
    val highlightedIconTint: Color,

    // Colors for item in a list
    val itemListBackgroundColor: Color,
    val itemListTextColor: Color,
    val itemListIconTint: Color,

    // Colors for checkbox components
    val checkedColor: Color,            // background when checked
    val checkmarkColor: Color,          // color of "✓"
    val uncheckedColor: Color,          // border color when unchecked
    val disabledColor: Color,

    // Colors for Dialogs
    val dialogBackgroundColor: Color,
    val dialogTextColor: Color,
    val dialogConfirmBtnTextColor: Color,
    val dialogConfirmBtnBackgroundColor: Color,
    val dialogCancelBtnTextColor: Color,
    val dialogCancelBtnBackgroundColor: Color,

    // Colors for BottomSheet
    val bottomSheetBackgroundColor: Color,
    val bottomSheetTextColor: Color,
    val bottomSheetIconTint: Color,

    // Colors for Switch
    val switchEnableColor: Color,
    val switchDisableColor: Color,
)