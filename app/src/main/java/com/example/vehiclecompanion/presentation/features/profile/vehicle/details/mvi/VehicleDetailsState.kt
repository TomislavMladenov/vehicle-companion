package com.example.vehiclecompanion.presentation.features.profile.vehicle.details.mvi

import com.example.vehiclecompanion.presentation.ui.model.SelectWrapper
import com.example.vehiclecompanion.presentation.ui.model.options.BrandMenuOption
import com.example.vehiclecompanion.presentation.ui.model.options.FuelTypeMenuOption
import com.example.vehiclecompanion.presentation.ui.model.options.ModelMenuOptions
import com.example.vehiclecompanion.presentation.ui.model.options.YearMenuOption
import com.example.vehiclecompanion.presentation.ui.model.options.buildFuelTypeMenuOptions

data class VehicleDetailsState(
    val isLoading: Boolean = false,
    val year: SelectWrapper<YearMenuOption> = SelectWrapper(),
    val brand: SelectWrapper<BrandMenuOption> = SelectWrapper(),
    val model: SelectWrapper<ModelMenuOptions> = SelectWrapper(),
    val fuelType: SelectWrapper<FuelTypeMenuOption> = SelectWrapper(buildFuelTypeMenuOptions())
) {
    val isModelEnabled: Boolean
        get() = brand.isValid
}