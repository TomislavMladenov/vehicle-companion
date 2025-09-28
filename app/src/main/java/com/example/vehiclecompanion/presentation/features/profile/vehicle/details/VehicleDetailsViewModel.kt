package com.example.vehiclecompanion.presentation.features.profile.vehicle.details

import com.example.vehiclecompanion.presentation.base.BaseViewModel
import com.example.vehiclecompanion.presentation.features.profile.vehicle.details.mvi.VehicleDetailsAction
import com.example.vehiclecompanion.presentation.features.profile.vehicle.details.mvi.VehicleDetailsState
import com.example.vehiclecompanion.presentation.ui.model.MenuOption
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class VehicleDetailsViewModel
@Inject
constructor() : BaseViewModel<VehicleDetailsState, VehicleDetailsAction>(VehicleDetailsState()) {

    private fun selectFuelType(typeOption: MenuOption) {
        val newList = state.value.fuelType.options.map {
            if (it.uuid == typeOption.uuid) {
                it.copy(checked = !it.checked)
            } else {
                it.copy(checked = false)

            }
        }
        updateState { copy(fuelType = state.value.fuelType.copy(options = newList)) }
    }

    override suspend fun handleActions(action: VehicleDetailsAction) {
        when (action) {
            is VehicleDetailsAction.OnSelectBrand -> {

            }
            is VehicleDetailsAction.OnSelectFuelType -> {
                selectFuelType(action.typeOption)
            }
            is VehicleDetailsAction.OnSelectModel -> {

            }
            is VehicleDetailsAction.OnSelectYear -> {

            }
            is VehicleDetailsAction.OnVINValueChange -> {

            }
        }
    }
}