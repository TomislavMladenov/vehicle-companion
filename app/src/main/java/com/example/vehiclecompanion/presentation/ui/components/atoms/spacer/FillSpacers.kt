package com.example.vehiclecompanion.presentation.ui.components.atoms.spacer

import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun ColumnScope.FillAvailableHeightSpacer() = Spacer(Modifier.weight(1f))

@Composable
fun RowScope.FillAvailableWidthSpacer() = Spacer(Modifier.weight(1f))