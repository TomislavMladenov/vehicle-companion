@file:OptIn(ExperimentalUuidApi::class)

package com.example.vehiclecompanion.presentation.ui.model.options

import com.example.vehiclecompanion.presentation.ui.model.DynamicString
import com.example.vehiclecompanion.presentation.ui.model.MenuOption
import com.example.vehiclecompanion.presentation.ui.model.TextResource
import com.example.vehiclecompanion.presentation.ui.model.VehicleBrandOption
import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid.Companion.random

data class BrandMenuOption(
    override val titleResId: TextResource,
    override val checked: Boolean = false,
    override val enabled: Boolean = true,
    val vehicleBrandOption: VehicleBrandOption
): MenuOption {
    override val uuid: String = random().toString()
}

fun List<VehicleBrandOption>.buildVehicleBrandOptions() = this.map { brandOption ->
    BrandMenuOption(
        titleResId = DynamicString(brandOption.name),
        vehicleBrandOption = brandOption
    )
}

