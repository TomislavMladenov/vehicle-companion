package com.example.vehiclecompanion.core.model

data class Vehicle(
    val year: String,
    val brand: VehicleBrand,
    val vin: String,
    val fuelType: FuelType,
    val photo: String
) {
    val name: String = "${brand.name} ${brand.model}"
}