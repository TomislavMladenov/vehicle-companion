package com.example.vehiclecompanion.presentation.features.garage.vehicle.list

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.vehiclecompanion.presentation.features.garage.vehicle.list.mvi.VehicleListAction
import kotlinx.coroutines.delay

@Composable
fun VehicleListScreen() {

    val viewModel = hiltViewModel<VehicleListViewModel>()
    val state by viewModel.state.collectAsStateWithLifecycle()

    LaunchedEffect(Unit) {
        // Simulate some loading fetching
        delay(800)
        viewModel.submitAction(VehicleListAction.GetVehicles)
    }

    VehicleListContent(state) {
        viewModel.submitAction(it)
    }
}