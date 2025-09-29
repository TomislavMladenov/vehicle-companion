package com.example.vehiclecompanion.presentation.features.garage.vehicle.list.mvi

import com.example.vehiclecompanion.core.model.Vehicle

data class VehicleListState(
    val initiallyLoaded: Boolean = false,
    val vehicles: List<Vehicle> = emptyList()
)