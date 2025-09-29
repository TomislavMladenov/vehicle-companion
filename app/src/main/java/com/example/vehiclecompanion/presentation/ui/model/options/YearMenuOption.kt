@file:OptIn(ExperimentalUuidApi::class)

package com.example.vehiclecompanion.presentation.ui.model.options

import com.example.vehiclecompanion.presentation.ui.model.DynamicString
import com.example.vehiclecompanion.presentation.ui.model.MenuOption
import com.example.vehiclecompanion.presentation.ui.model.TextResource
import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid.Companion.random

val supportedYears = listOf("2025","2024","2023", "2022", "2021", "2020", "2019", "2018", "2017", "2016", "2015")

data class YearMenuOption(
    override val checked: Boolean = false,
    override val enabled: Boolean = true,
    val value: String
): MenuOption {
    override val uuid: String = random().toString()
    override val titleResId: TextResource = DynamicString(value)
}

fun List<String>.buildYearMenuOptions(): List<YearMenuOption> {
    return this.map { year -> YearMenuOption(value = year) }
}
