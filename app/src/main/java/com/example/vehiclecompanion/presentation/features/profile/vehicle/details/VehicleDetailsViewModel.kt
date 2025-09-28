package com.example.vehiclecompanion.presentation.features.profile.vehicle.details

import androidx.lifecycle.SavedStateHandle
import com.example.vehiclecompanion.core.util.Constants
import com.example.vehiclecompanion.presentation.base.BaseViewModel
import com.example.vehiclecompanion.presentation.features.profile.vehicle.details.mvi.VehicleDetailsAction
import com.example.vehiclecompanion.presentation.features.profile.vehicle.details.mvi.VehicleDetailsState
import com.example.vehiclecompanion.presentation.ui.model.MenuOption
import com.example.vehiclecompanion.presentation.ui.model.options.buildModelMenuOptions
import dagger.hilt.android.lifecycle.HiltViewModel
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class VehicleDetailsViewModel
@Inject
constructor(
    savedStateHandle: SavedStateHandle,
) : BaseViewModel<VehicleDetailsState, VehicleDetailsAction>(VehicleDetailsState()) {

    private val vehicleId: String =
        checkNotNull(savedStateHandle[Constants.NavArgs.VEHICLE_ID])

    init {
        Timber.d("Vehicle id: $vehicleId")
    }

    private fun selectYear(yearOption: MenuOption) {
        val updatedListOptions = state.value.year.options.map {
            if (it.uuid == yearOption.uuid) {
                it.copy(checked = !it.checked)
            } else {
                it.copy(checked = false)
            }
        }
        updateState { copy(year = state.value.year.copy(options = updatedListOptions)) }
    }

    private fun selectBrandType(brandType: MenuOption) {
        val models: MutableList<String> = mutableListOf()
        val updatedListOptions = state.value.brand.options.map {
            if (it.uuid == brandType.uuid) {
                models.addAll(it.vehicleBrandOption.models)
                it.copy(checked = !it.checked)
            } else {
                it.copy(checked = false)
            }
        }

        val newModelOptions = models.buildModelMenuOptions()
        updateState {
            copy(
                brand = state.value.brand.copy(options = updatedListOptions),
                model = state.value.model.copy(options = newModelOptions),
            )
        }
    }

    private fun selectModel(modelOption: MenuOption) {
        val updatedListOptions = state.value.model.options.map {
            if (it.uuid == modelOption.uuid) {
                it.copy(checked = !it.checked)
            } else {
                it.copy(checked = false)
            }
        }
        updateState {
            copy(model = state.value.model.copy(options = updatedListOptions),)
        }
    }

    private fun selectFuelType(typeOption: MenuOption) {
        val updatedListOptions = state.value.fuelType.options.map {
            if (it.uuid == typeOption.uuid) {
                it.copy(checked = !it.checked)
            } else {
                it.copy(checked = false)

            }
        }
        updateState { copy(fuelType = state.value.fuelType.copy(options = updatedListOptions)) }
    }

    override suspend fun handleActions(action: VehicleDetailsAction) {
        when (action) {
            is VehicleDetailsAction.OnSelectYear -> selectYear(action.yearOption)
            is VehicleDetailsAction.OnSelectBrand -> selectBrandType(action.brandOption)
            is VehicleDetailsAction.OnSelectModel -> selectModel(action.modelOption)
            is VehicleDetailsAction.OnSelectFuelType -> selectFuelType(action.typeOption)
            is VehicleDetailsAction.OnVINValueChange -> {

            }
        }
    }
}