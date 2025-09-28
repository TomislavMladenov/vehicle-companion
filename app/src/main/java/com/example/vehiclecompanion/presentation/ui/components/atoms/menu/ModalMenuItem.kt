package com.example.vehiclecompanion.presentation.ui.components.atoms.menu

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.vehiclecompanion.presentation.ui.model.TextResource
import com.example.vehiclecompanion.presentation.ui.model.textResource
import com.example.vehiclecompanion.presentation.ui.theme.Pink80
import com.example.vehiclecompanion.presentation.ui.theme.Purple40

@Composable
fun ModalMenuItem(
    text: TextResource,
    selected: Boolean,
    enabled: Boolean,
    onClick: () -> Unit,
) {
    val contentColor = when {
        !enabled -> MaterialTheme.colorScheme.onSurface.copy(alpha = .6f)
        selected -> Pink80.copy(alpha = 1f)
        else -> MaterialTheme.colorScheme.onSurface.copy(alpha = 1f)
    }

    CompositionLocalProvider(LocalContentColor provides contentColor) {
        Box(
            modifier = Modifier
                .clickable(enabled) { onClick() }
                .fillMaxWidth()
                .padding(16.dp)) {
            Text(
                text = textResource(text),
                style = MaterialTheme.typography.titleSmall,
            )
        }
    }
}