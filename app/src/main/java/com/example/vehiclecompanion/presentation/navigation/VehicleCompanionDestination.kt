package com.example.vehiclecompanion.presentation.navigation

sealed class VehicleCompanionDestination(
    val destination: String,
) {
    data object ListView : VehicleCompanionDestination("list")
    data object Map: VehicleCompanionDestination("map")
}


