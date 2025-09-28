@file:OptIn(ExperimentalUuidApi::class)

package com.example.vehiclecompanion.presentation.ui.model.options

import com.example.vehiclecompanion.presentation.ui.model.DynamicString
import com.example.vehiclecompanion.presentation.ui.model.MenuOption
import com.example.vehiclecompanion.presentation.ui.model.TextResource
import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid.Companion.random

data class YearMenuOption(
    override val titleResId: TextResource,
    override val checked: Boolean = false,
    override val enabled: Boolean = true
): MenuOption {
    override val uuid: String = random().toString()
}

fun List<String>.buildYearMenuOptions(): List<YearMenuOption> {
    return this.map { year ->
        YearMenuOption(
            titleResId = DynamicString(year),
        )
    }
}
