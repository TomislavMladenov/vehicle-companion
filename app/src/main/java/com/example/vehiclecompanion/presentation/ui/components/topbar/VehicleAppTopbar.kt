@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.vehiclecompanion.presentation.ui.components.topbar

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.vehiclecompanion.presentation.navigation.Profile

@Composable
fun VehicleAppTopbar(
    navHostController: NavHostController,
    onBack: () -> Unit
) {
    TopAppBar(
        title = {
            Text(
                text = "Vehicle Companion",
                color = Color.Black
            )
        },
        navigationIcon = {
            if (isDetails(navHostController)) {
                IconButton(onBack) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = "back action"
                    )
                }
            }
        },
        colors = TopAppBarDefaults.topAppBarColors(MaterialTheme.colorScheme.primaryContainer),
    )
}

@Composable
fun isDetails(navController: NavController): Boolean {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDest = navBackStackEntry?.destination?.route ?: ""
    return currentDest == Profile.VehicleDetails().route
}