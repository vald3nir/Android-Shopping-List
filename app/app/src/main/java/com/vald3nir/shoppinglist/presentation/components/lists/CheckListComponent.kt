package com.vald3nir.shoppinglist.presentation.components.lists

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.vald3nir.shoppinglist.domain.utils.orFalse

@Composable
fun BuildCheckListComponent(
    items: List<ItemCheckListModel>,
    modifier: Modifier = Modifier
) {
    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        itemsIndexed(items) { _, item ->
            BuildCheckItemListComponent(item, defaultBackgroundColor = MaterialTheme.colorScheme.primaryContainer)
        }
    }
}

@Composable
private fun BuildCheckItemListComponent(item: ItemCheckListModel, defaultBackgroundColor: Color) {
    var checkedState by remember { mutableStateOf(item.isChecked.orFalse()) }
    var backgroundColorState by remember { mutableStateOf(Color.White) }
    var textDecorationState by remember { mutableStateOf(TextDecoration.None) }

    val changeItemStatus: (Boolean) -> Unit = { isChecked ->
        item.isChecked = isChecked
        checkedState = isChecked
        backgroundColorState = if (isChecked) defaultBackgroundColor else Color.White
        textDecorationState = if (isChecked) TextDecoration.LineThrough else TextDecoration.None
    }

    Card(
        elevation = CardDefaults.cardElevation(
            defaultElevation = 10.dp
        ),
        modifier = Modifier
            .fillMaxWidth()
            .padding(15.dp)
            .clickable { changeItemStatus(!checkedState) }
    ) {
        Row(
            modifier = Modifier
                .background(backgroundColorState)
                .fillMaxWidth()
                .padding(vertical = 8.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            Checkbox(
                checked = checkedState,
                onCheckedChange = { changeItemStatus(it) }
            )
            Text(
                text = item.title.orEmpty(),
                fontSize = 18.sp,
                textDecoration = textDecorationState,
                modifier = Modifier.weight(1f)
            )
        }
    }
}