package com.example.vehiclecompanion.presentation.ui.components.atoms.loading

import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.vehiclecompanion.presentation.ui.theme.VCDeepBlue

@Composable
fun VCLoadingView(
    modifier: Modifier
) {
    CircularProgressIndicator(
        modifier = modifier,
        color = VCDeepBlue
    )
}