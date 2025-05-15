package com.vald3nir.shoppinglist.presentation.features.shoppingList.components.itemList

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewModelScope
import com.vald3nir.shoppinglist.domain.dto.ProductDTO
import com.vald3nir.shoppinglist.repository.ShoppingListRepository
import com.vald3nir.toolkit.compose.components.base.ToolkitIcons
import com.vald3nir.toolkit.compose.components.inputs.ToolkitAutoCompleteInputComponent
import com.vald3nir.toolkit.compose.components.inputs.ToolkitAutoCompleteInputData
import com.vald3nir.toolkit.compose.designSystem.schema.ScreenColorSchema
import com.vald3nir.toolkit.helpers.baseclasses.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class InputProductNameViewModel @Inject constructor(repository: ShoppingListRepository) : BaseViewModel() {
    val productsList: Flow<List<ProductDTO>> = repository.getAllProducts().stateIn(viewModelScope, SharingStarted.Lazily, emptyList())
}

private fun List<ProductDTO>.formatProducts() = map { ToolkitAutoCompleteInputData(id = it.id, text = it.name.orEmpty()) }

@Composable
fun InputProductNameComponent(
    inputValue: String,
    useTransparentBackend: Boolean = false,
    onSelected: (String) -> Unit,
    colors: ScreenColorSchema,
) {

    val viewModel = hiltViewModel<InputProductNameViewModel>()
    val products by viewModel.productsList.collectAsState(initial = emptyList())

    ToolkitAutoCompleteInputComponent(
        useTransparentBackend = useTransparentBackend,
        inputValue = inputValue,
        suggestionList = products.formatProducts(),
        colors = colors,
        label = "Nome do produto",
        placeholder = "Produto",
        startIcon = ToolkitIcons.ShoppingBasket,
        onSelected = onSelected,
    )
}