package com.example.vehiclecompanion.presentation.ui.components.loading

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.example.vehiclecompanion.presentation.ui.components.atoms.loading.VCLoadingView

@Composable
fun FullScreenCircularLoading() {
    Box(modifier = Modifier.background(Color.Transparent).fillMaxSize()) {
        VCLoadingView(Modifier.align(Alignment.Center))
    }
}