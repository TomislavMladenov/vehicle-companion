@file:OptIn(ExperimentalUuidApi::class)

package com.example.vehiclecompanion.presentation.ui.model.options

import com.example.vehiclecompanion.core.model.FuelType
import com.example.vehiclecompanion.presentation.ui.model.DynamicString
import com.example.vehiclecompanion.presentation.ui.model.MenuOption
import com.example.vehiclecompanion.presentation.ui.model.TextResource
import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid.Companion.random

data class FuelTypeMenuOption(
    override val checked: Boolean = false,
    override val enabled: Boolean = true,
    val fuelType: FuelType
) : MenuOption {
    override val uuid: String = random().toString()
    override val titleResId: TextResource = DynamicString(fuelType.name)
}

fun buildFuelTypeMenuOptions() = FuelType.entries.map { type ->
    FuelTypeMenuOption(fuelType = type)
}