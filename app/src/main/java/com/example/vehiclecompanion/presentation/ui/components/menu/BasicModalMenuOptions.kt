package com.example.vehiclecompanion.presentation.ui.components.menu

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.example.vehiclecompanion.presentation.ui.components.atoms.menu.ModalMenuItem
import com.example.vehiclecompanion.presentation.ui.components.atoms.menu.SelectedModalMenuItem
import com.example.vehiclecompanion.presentation.ui.model.DynamicString
import com.example.vehiclecompanion.presentation.ui.model.MenuOption

@Composable
fun BasicModalMenuOptions(
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    label: String,
    items: List<MenuOption>,
    onItemSelected: (item: MenuOption) -> Unit,
    drawItem: @Composable (MenuOption, Boolean, Boolean, () -> Unit) -> Unit = { item, selected, itemEnabled, onClick ->
        ModalMenuItem(
            text = item.titleResId,
            selected = selected,
            enabled = itemEnabled,
            onClick = onClick,
        )
    },
) {
    var expanded by remember { mutableStateOf(false) }

    SelectedModalMenuItem(
        modifier = modifier,
        label = label,
        enabled = enabled,
        expanded = expanded,
        selectedIndex = items.indexOfFirst{ it.checked },
        items = items,
    ) {
        expanded = true
    }

    if (expanded) {
        Dialog(
            onDismissRequest = { expanded = false },
        ) {
            ModalBasicListContent(
                selectedIndex = items.indexOfFirst { it.checked },
                items = items,
                onItemSelected = { index, item ->
                    expanded = false
                    onItemSelected(item)
                },
                drawItem = drawItem
            )
        }
    }
}

@Composable
internal fun <T> ModalBasicListContent(
    selectedIndex: Int,
    notSetLabel: String? = null,
    items: List<T>,
    onItemSelected: (index: Int, item: T) -> Unit,
    drawItem: @Composable (T, Boolean, Boolean, () -> Unit) -> Unit
) {
    MaterialTheme {
        Surface(
            shape = RoundedCornerShape(12.dp),
        ) {
            val listState = rememberLazyListState()
            if (selectedIndex > -1) {
                LaunchedEffect("ScrollToSelected") {
                    listState.scrollToItem(index = selectedIndex)
                }
            }

            LazyColumn(modifier = Modifier.fillMaxWidth(), state = listState) {
                if (notSetLabel != null) {
                    item {
                        ModalMenuItem(
                            text = DynamicString(notSetLabel),
                            selected = false,
                            enabled = false,
                            onClick = { },
                        )
                    }
                }

                itemsIndexed(items) { index, item ->
                    val selectedItem = index == selectedIndex
                    drawItem(
                        item,
                        selectedItem,
                        true
                    ) {
                        onItemSelected(index, item)
                    }

                    if (index < items.lastIndex) {
                        HorizontalDivider(modifier = Modifier.padding(horizontal = 16.dp))
                    }
                }
            }
        }
    }
}