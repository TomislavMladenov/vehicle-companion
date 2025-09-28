package com.example.vehiclecompanion.presentation.ui.model

interface MenuOption {
    val uuid: String
    val titleResId: TextResource
    val checked: Boolean
    val enabled: Boolean
}

interface MenuOptionGroup {
    val uuid: String
    val value: String
    val options: List<MenuOption>
}