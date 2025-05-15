package com.vald3nir.shoppinglist.presentation.features.shoppingList.screens.home

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.vald3nir.shoppinglist.R
import com.vald3nir.shoppinglist.domain.dto.ShoppingListDTO
import com.vald3nir.shoppinglist.domain.mocks.MockShoppingListDTO
import com.vald3nir.shoppinglist.presentation.features.shoppingList.components.itemList.ShoppingListResumeSectionComponent
import com.vald3nir.toolkit.compose.components.base.DefaultSpaceHeight
import com.vald3nir.toolkit.compose.components.base.ToolkitFloatingButton
import com.vald3nir.toolkit.compose.components.base.ToolkitIcons
import com.vald3nir.toolkit.compose.components.inputs.ToolkitSearchFieldComponent
import com.vald3nir.toolkit.compose.components.toolbars.ToolkitToolbarWithAvatarComponent
import com.vald3nir.toolkit.compose.designSystem.AppTheme
import com.vald3nir.toolkit.compose.designSystem.AppThemeViewModel
import com.vald3nir.toolkit.compose.designSystem.DefaultThemeColors
import com.vald3nir.toolkit.compose.designSystem.schema.ScreenColorSchema
import com.vald3nir.toolkit.compose.templates.ToolkitBaseContainer
import com.vald3nir.toolkit.compose.templates.ToolkitEmptyStateScreen
import com.vald3nir.toolkit.helpers.utils.orFalse
import kotlinx.coroutines.launch

@Composable
internal fun HomeScreenScope.HomeScreen() {
    NavigationObserver()
    val appThemeViewModel: AppThemeViewModel = hiltViewModel()
    val context = LocalContext.current
    var isDarkMode: Boolean by remember {
        mutableStateOf(appThemeViewModel.isDarkMode(context))
    }

    var isLoading by remember { mutableStateOf(true) }
    val snackBarHostState = remember { SnackbarHostState() }
    val searchQuery by viewModel.searchQuery.collectAsState()
    val shoppingLists by viewModel.filteredList.collectAsState()
    val coroutineScope = rememberCoroutineScope()

    CollectUiState(
        onShowMessage = { message ->
            coroutineScope.launch {
                snackBarHostState.showSnackbar(message)
            }
        },
        onLoading = { isLoading = it }
    )

    AppTheme {
        HomeContent(
            lists = shoppingLists,
            isDarkMode = isDarkMode,
            colors = appThemeViewModel.currentTheme(context),
            scope = this,
            searchQuery = searchQuery,
            onQueryChange = updateSearchQueryEvent,
            userImageUrl = userPhotoUrl(),
            onClickAddNewList = { redirectToCreateNewList() },
            onChangeTheme = {
                isDarkMode = !isDarkMode
                appThemeViewModel.updateTheme(isDarkMode)
            },
            snackBarHostState = snackBarHostState
        )
    }
}

@Composable
private fun HomeContent(
    scope: HomeScreenScope? = null,
    lists: List<ShoppingListDTO>,
    isDarkMode: Boolean = false,
    colors: ScreenColorSchema,
    searchQuery: String = "",
    onQueryChange: (String) -> Unit = {},
    userImageUrl: String? = null,
    onClickAddNewList: () -> Unit = {},
    onChangeTheme: (Boolean) -> Unit = {},
    snackBarHostState: SnackbarHostState = SnackbarHostState(),
) {
    val context = LocalContext.current
    var showMenu: Boolean by remember { mutableStateOf(false) }
    ToolkitBaseContainer(
        backgroundColor = colors.backgroundColor,
        snackBarHostState = snackBarHostState,
        topBarContent = {
            ToolkitToolbarWithAvatarComponent(
                title = stringResource(R.string.my_lists),
                userImageUrl = userImageUrl,
                colors = colors,
                onAvatarClick = { showMenu = true }
            )
        },
        floatingActionButton = {
            if (lists.isNotEmpty()) {
                ToolkitFloatingButton(
                    containerColor = colors.highlightedBackgroundColor,
                    contentColor = colors.highlightedIconTint,
                    onClick = onClickAddNewList
                )
            }
        },
        content = {
            ToolkitSearchFieldComponent(
                searchQuery = searchQuery,
                onQueryChange = onQueryChange,
                useUnderline = true,
                placeholder = stringResource(R.string.research),
                colors = colors,
            )
            if (lists.isEmpty()) {
                ToolkitEmptyStateScreen(
                    title = "Sem itens cadastrados",
                    imageVector = ToolkitIcons.Inbox,
                    colors = colors,
                    onAddClick = onClickAddNewList,
                )
            } else {
                DefaultSpaceHeight()
                LazyColumn {
                    itemsIndexed(lists) { _, item ->
                        ShoppingListResumeSectionComponent(
                            list = item,
                            colors = colors,
                            onClick = {
                                scope?.redirectToShowList(item.id)
                            })
                        DefaultSpaceHeight()
                    }
                }
            }
            if (showMenu) {
                HomeMenuComponent(
                    colors = colors,
                    onDismissRequest = { showMenu = false },
                    isUserLogged = scope?.isUserLogged().orFalse(),
                    isDarkMode = isDarkMode,
                    onChangeTheme = onChangeTheme,
                    onChangeUser = { scope?.onChangeUser(context) }
                )
            }
        }
    )
}

@Preview(showBackground = true, backgroundColor = 0xFF808080)
@Composable
private fun PreviewLight() {
    HomeContent(
        lists = MockShoppingListDTO.lists(),
        colors = DefaultThemeColors().lightColors,
    )
}

@Preview(showBackground = true, backgroundColor = 0xFF808080)
@Composable
private fun PreviewDark() {
    HomeContent(
        lists = MockShoppingListDTO.lists(),
        colors = DefaultThemeColors().darkColors,
    )
}