package com.example.vehiclecompanion.presentation.ui.model

sealed class VehicleBrandOption(
    val name: String,
    val models: List<String> = emptyList()
) {
    object BMW : VehicleBrandOption("BMW")
    object LandRover : VehicleBrandOption("Land Rover")
    object Audi : VehicleBrandOption("Audi")
    object Chrysler : VehicleBrandOption("Chrysler")
    object Ford : VehicleBrandOption("Ford")
}