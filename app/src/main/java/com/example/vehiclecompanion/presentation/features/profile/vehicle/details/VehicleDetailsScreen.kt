package com.example.vehiclecompanion.presentation.features.profile.vehicle.details

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle

@Composable
fun VehicleDetailsScreen() {

    val viewModel = hiltViewModel<VehicleDetailsViewModel>()
    val state by viewModel.state.collectAsStateWithLifecycle()

    VehicleDetailsContent(
        state = state,
        action = { viewModel.submitAction(it) }
    )

}