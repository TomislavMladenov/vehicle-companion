@file:OptIn(ExperimentalUuidApi::class)

package com.example.vehiclecompanion.presentation.ui.model.options

import com.example.vehiclecompanion.presentation.ui.model.DynamicString
import com.example.vehiclecompanion.presentation.ui.model.MenuOption
import com.example.vehiclecompanion.presentation.ui.model.TextResource
import com.example.vehiclecompanion.presentation.ui.model.VehicleBrandOption
import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid.Companion.random

val supportedBrands = listOf(
    VehicleBrandOption.Audi,
    VehicleBrandOption.BMW,
    VehicleBrandOption.LandRover,
    VehicleBrandOption.Chrysler,
    VehicleBrandOption.Ford
)

data class BrandMenuOption(
    override val checked: Boolean = false,
    override val enabled: Boolean = true,
    val vehicleBrandOption: VehicleBrandOption
): MenuOption {
    override val uuid: String = random().toString()
    override val titleResId: TextResource = DynamicString(vehicleBrandOption.name)
}

fun List<VehicleBrandOption>.buildVehicleBrandOptions() = this.map { brandOption ->
    BrandMenuOption(vehicleBrandOption = brandOption)
}

