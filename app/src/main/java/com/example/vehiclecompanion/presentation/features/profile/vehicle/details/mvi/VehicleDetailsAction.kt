package com.example.vehiclecompanion.presentation.features.profile.vehicle.details.mvi

interface VehicleDetailsAction {

    data class OnNameValueChange(val name: String): VehicleDetailsAction

    data class OnModelValueChange(val model: String): VehicleDetailsAction

    data class OnYearValueChange(val year: String): VehicleDetailsAction

    data class OnVINValueChange(val vin: String): VehicleDetailsAction

    data class OnSelectFuelType(val type: String): VehicleDetailsAction
}