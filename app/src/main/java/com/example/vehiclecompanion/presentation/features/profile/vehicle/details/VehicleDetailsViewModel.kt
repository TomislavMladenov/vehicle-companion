package com.example.vehiclecompanion.presentation.features.profile.vehicle.details

import com.example.vehiclecompanion.presentation.base.BaseViewModel
import com.example.vehiclecompanion.presentation.features.profile.vehicle.details.mvi.VehicleDetailsAction
import com.example.vehiclecompanion.presentation.features.profile.vehicle.details.mvi.VehicleDetailsState
import dagger.hilt.android.lifecycle.HiltViewModel

@HiltViewModel
class VehicleDetailsViewModel(

) : BaseViewModel<VehicleDetailsState, VehicleDetailsAction>(VehicleDetailsState()) {
}