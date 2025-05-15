package com.vald3nir.shoppinglist.presentation.features.shoppingList.screens.selectType

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.vald3nir.shoppinglist.domain.enums.ProductCategoryEnum
import com.vald3nir.toolkit.compose.components.base.DefaultSpaceHeight
import com.vald3nir.toolkit.compose.components.base.ToolkitCard
import com.vald3nir.toolkit.compose.components.base.ToolkitIcon
import com.vald3nir.toolkit.compose.components.base.ToolkitIcons
import com.vald3nir.toolkit.compose.components.base.ToolkitRow
import com.vald3nir.toolkit.compose.components.base.ToolkitText
import com.vald3nir.toolkit.compose.components.toolbars.ToolkitGenericToolbarComponent
import com.vald3nir.toolkit.compose.designSystem.AppTheme
import com.vald3nir.toolkit.compose.designSystem.AppThemeViewModel
import com.vald3nir.toolkit.compose.designSystem.DefaultThemeColors
import com.vald3nir.toolkit.compose.designSystem.schema.ScreenColorSchema
import com.vald3nir.toolkit.compose.templates.ToolkitBaseContainer
import kotlinx.coroutines.launch


@Composable
fun SelectTypeListScope.SelectTypeListScreen() {
    NavigationObserver()
    val appThemeViewModel: AppThemeViewModel = hiltViewModel()
    val snackBarHostState = remember { SnackbarHostState() }
    val coroutineScope = rememberCoroutineScope()
    val context = LocalContext.current

    CollectUiState(
        onShowMessage = { message ->
            coroutineScope.launch {
                snackBarHostState.showSnackbar(message)
            }
        },
    )

    AppTheme {
        SelectTypeListContent(
            colors = appThemeViewModel.currentTheme(context),
            snackBarHostState = snackBarHostState
        )
    }
}

@Composable
private fun SelectTypeListContent(
    colors: ScreenColorSchema,
    snackBarHostState: SnackbarHostState = SnackbarHostState(),
) {
    ToolkitBaseContainer(
        backgroundColor = colors.backgroundColor,
        snackBarHostState = snackBarHostState,
        topBarContent = {
            ToolkitGenericToolbarComponent(
                title = "Criação de Listas",
                colors = colors,
            )
        },
        content = {
            Column(modifier = Modifier.fillMaxWidth(), verticalArrangement = Arrangement.Center) {
                // Faixa verde com texto
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(Color(0xFF4CAF50))
                        .padding(vertical = 8.dp)
                ) {
                    ToolkitText.Label(
                        text = "Listas Pré Preenchidas",
                        textColor = colors.textColor
                    )
                }

                DefaultSpaceHeight()

                ToolkitText.Label(
                    text = "As listas pré preenchidas contêm os itens mais buscados para cada segmento escolhido. Ou se preferir, você pode criar uma lista em branco.",
                    textColor = colors.textColor
                )

                DefaultSpaceHeight()

                ToolkitText.Link(
                    text = "Criar Lista em branco",
                    textColor = colors.linkColor,
                    modifier = Modifier.fillMaxWidth(),
                    textAlign = TextAlign.Center
                )

                DefaultSpaceHeight()

                ProductCategoryEnum.entries.forEach { category ->
                    ToolkitCard(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable { /* ação ao clicar */ },
                        backgroundColor = colors.itemListBackgroundColor
                    ) {
                        ToolkitRow {
                            Image(
                                painter = painterResource(id = category.icon),
                                contentDescription = "nome",
//                                modifier = Modifier
//                                    .size(24.dp)
//                                    .padding(end = 12.dp)
                            )
                            ToolkitText.Label(text = category.alias, textColor = colors.textColor)
                            ToolkitIcon(imageVector = ToolkitIcons.ChevronRight)
                        }
                    }
                    DefaultSpaceHeight()
                }
            }
        }
    )
}

@Preview(showBackground = true, backgroundColor = 0xFF808080)
@Composable
private fun Preview() {
    SelectTypeListContent(
//        colors = DefaultThemeColors().lightColors,
        colors = DefaultThemeColors().darkColors,
    )
}