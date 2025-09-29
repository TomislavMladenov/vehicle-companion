package com.example.vehiclecompanion.presentation.features.garage.vehicle.list.mvi

import com.example.vehiclecompanion.core.model.Vehicle

sealed interface VehicleListAction {

    data object GetVehicles : VehicleListAction

    data class OnSelectVehicle(val vehicle: Vehicle): VehicleListAction

    data object OnNewVehicle : VehicleListAction
}