package com.example.vehiclecompanion.presentation.features.profile.vehicle.details.mvi

import com.example.vehiclecompanion.presentation.ui.model.SelectWrapper
import com.example.vehiclecompanion.presentation.ui.model.TextResource
import com.example.vehiclecompanion.presentation.ui.model.options.BrandMenuOption
import com.example.vehiclecompanion.presentation.ui.model.options.FuelTypeMenuOption
import com.example.vehiclecompanion.presentation.ui.model.options.ModelMenuOptions
import com.example.vehiclecompanion.presentation.ui.model.options.YearMenuOption
import com.example.vehiclecompanion.presentation.ui.model.options.buildFuelTypeMenuOptions
import com.example.vehiclecompanion.presentation.ui.model.options.buildVehicleBrandOptions
import com.example.vehiclecompanion.presentation.ui.model.options.buildYearMenuOptions
import com.example.vehiclecompanion.presentation.ui.model.options.supportedBrands
import com.example.vehiclecompanion.presentation.ui.model.options.supportedYears

data class VehicleDetailsState(
    val isLoading: Boolean = false,
    val year: SelectWrapper<YearMenuOption> = SelectWrapper(supportedYears.buildYearMenuOptions()),
    val brand: SelectWrapper<BrandMenuOption> = SelectWrapper(supportedBrands.buildVehicleBrandOptions()),
    val model: SelectWrapper<ModelMenuOptions> = SelectWrapper(emptyList()),
    val fuelType: SelectWrapper<FuelTypeMenuOption> = SelectWrapper(buildFuelTypeMenuOptions()),

) {
    val name: Pair<TextResource?, TextResource?>
        get() = Pair(brand.selected?.titleResId, model.selected?.titleResId)

    val isSaveEnabled: Boolean
        get() = year.isValid && brand.isValid && model.isValid && fuelType.isValid
}