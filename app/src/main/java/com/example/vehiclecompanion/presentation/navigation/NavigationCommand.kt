package com.example.vehiclecompanion.presentation.navigation

sealed class NavigationCommand {
    data class Navigate(val destination: VehicleCompanionDestination): NavigationCommand()
    data object Back : NavigationCommand()
}