package com.example.vehiclecompanion.presentation.ui.model

sealed class VehicleBrandOption(
    val name: String,
    val models: List<String> = emptyList()
) {
    object BMW : VehicleBrandOption("BMW", listOf("3 Series", "5 Series", "7 Series"))
    object LandRover : VehicleBrandOption("Land Rover", listOf("Discovery", "Range Rover"))
    object Audi : VehicleBrandOption("Audi")
    object Chrysler : VehicleBrandOption("Chrysler")
    object Ford : VehicleBrandOption("Ford")
}