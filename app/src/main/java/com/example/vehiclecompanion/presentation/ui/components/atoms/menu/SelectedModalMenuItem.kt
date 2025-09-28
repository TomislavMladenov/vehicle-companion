package com.example.vehiclecompanion.presentation.ui.components.atoms.menu

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.vehiclecompanion.presentation.ui.model.MenuOption
import com.example.vehiclecompanion.presentation.ui.model.textResource

@Composable
internal fun SelectedModalMenuItem(
    modifier: Modifier,
    label: String,
    enabled: Boolean,
    expanded: Boolean,
    selectedIndex: Int,
    items: List<MenuOption>,
    onClick: () -> Unit
) {
    Box(modifier = modifier.height(IntrinsicSize.Min)) {
        OutlinedTextField(
            label = { Text(label) },
            value = items.getOrNull(selectedIndex)?.let { textResource(resource = it.titleResId) } ?: "",
            enabled = enabled,
            modifier = Modifier.fillMaxWidth(),
            trailingIcon = {
                val icon = if (expanded) Icons.Filled.KeyboardArrowUp else Icons.Filled.KeyboardArrowDown
                Icon(icon, "")
            },
            onValueChange = { },
            readOnly = true,
        )

        // Transparent clickable surface on top of OutlinedTextField
        Surface(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 8.dp)
                .clip(MaterialTheme.shapes.extraSmall)
                .clickable(enabled = enabled) { onClick() },
            color = Color.Transparent,
        ) {}
    }
}