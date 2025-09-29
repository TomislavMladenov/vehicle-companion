package com.example.vehiclecompanion.presentation.features.garage.vehicle.details.mvi

import com.example.vehiclecompanion.presentation.ui.model.MenuOption

interface VehicleDetailsAction {

    data class OnSelectYear(val yearOption: MenuOption): VehicleDetailsAction
    data class OnSelectBrand(val brandOption: MenuOption): VehicleDetailsAction
    data class OnSelectModel(val modelOption: MenuOption): VehicleDetailsAction
    data class OnSelectFuelType(val typeOption: MenuOption): VehicleDetailsAction
    data class OnVINValueChange(val vin: String): VehicleDetailsAction

    data object OnSave : VehicleDetailsAction
}


