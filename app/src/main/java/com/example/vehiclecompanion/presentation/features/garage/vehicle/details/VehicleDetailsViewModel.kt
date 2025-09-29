package com.example.vehiclecompanion.presentation.features.garage.vehicle.details

import android.view.Display
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.example.vehiclecompanion.core.model.FuelType
import com.example.vehiclecompanion.core.model.Vehicle
import com.example.vehiclecompanion.core.model.VehicleBrand
import com.example.vehiclecompanion.core.util.Constants
import com.example.vehiclecompanion.core.vehicle.repository.VehicleRepository
import com.example.vehiclecompanion.presentation.base.BaseViewModel
import com.example.vehiclecompanion.presentation.features.garage.vehicle.details.mvi.VehicleDetailsAction
import com.example.vehiclecompanion.presentation.features.garage.vehicle.details.mvi.VehicleDetailsState
import com.example.vehiclecompanion.presentation.navigation.NavigationCommand
import com.example.vehiclecompanion.presentation.navigation.NavigationManager
import com.example.vehiclecompanion.presentation.navigation.VehicleCompanionDestination
import com.example.vehiclecompanion.presentation.ui.model.MenuOption
import com.example.vehiclecompanion.presentation.ui.model.SelectWrapper
import com.example.vehiclecompanion.presentation.ui.model.options.BrandMenuOption
import com.example.vehiclecompanion.presentation.ui.model.options.FuelTypeMenuOption
import com.example.vehiclecompanion.presentation.ui.model.options.ModelMenuOptions
import com.example.vehiclecompanion.presentation.ui.model.options.YearMenuOption
import com.example.vehiclecompanion.presentation.ui.model.options.buildModelMenuOptions
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject
import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid.Companion.random

@HiltViewModel
class VehicleDetailsViewModel
@Inject
constructor(
    savedStateHandle: SavedStateHandle,
    private val vehicleRepository: VehicleRepository,
    private val navigationManager: NavigationManager
) : BaseViewModel<VehicleDetailsState, VehicleDetailsAction>(VehicleDetailsState()) {

    private val vehicleUuid: String =
        checkNotNull(savedStateHandle[Constants.NavArgs.VEHICLE_UUID])

    init {
        Timber.d("Vehicle id: $vehicleUuid")
        if (vehicleUuid != Constants.DefValue.NO_VEHICLE_ID) {
            viewModelScope.launch { getExistingVehicle(vehicleUuid) }
        }
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
            copy(model = state.value.model.copy(options = updatedListOptions))
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

    private suspend fun save() {
        val vehicle = buildVehicle()
        vehicle?.let {
            vehicleRepository.save(it)
            navigationManager.navigate(NavigationCommand.Navigate(VehicleCompanionDestination.VehicleList))
        }
    }

    private suspend fun getExistingVehicle(uuid: String) {
        vehicleRepository.getById(uuid)?.let { vehicle ->
            val brandAndModel = preloadBrandAndModel(vehicle.brand)
            updateState {
                copy(
                    year = preloadYear(vehicle.year),
                    brand = brandAndModel.first,
                    model = brandAndModel.second,
                    fuelType = preloadFuelType(vehicle.fuelType),
                    uuid = uuid
                )
            }
        }
    }

    private fun preloadYear(year: String): SelectWrapper<YearMenuOption> {
        val updatedYearOptions = state.value.year.options.map {
            if (it.value == year) {
                it.copy(checked = true)
            } else {
                it.copy(checked = false)
            }
        }
        return state.value.year.copy(options = updatedYearOptions)
    }

    private fun preloadBrandAndModel(brand: VehicleBrand): Pair<SelectWrapper<BrandMenuOption>, SelectWrapper<ModelMenuOptions>> {
        val models: MutableList<String> = mutableListOf()
        val updatedBrandOptions = state.value.brand.options.map {
            if (it.vehicleBrandOption.name == brand.name) {
                models.addAll(it.vehicleBrandOption.models)
                it.copy(checked = true)
            } else {
                it.copy(checked = false)
            }
        }

        val updatedModelOptions = models.buildModelMenuOptions().map {
            if (it.model == brand.model) {
                it.copy(checked = true)
            } else {
                it.copy(checked = false)
            }
        }
        return Pair(
            first = state.value.brand.copy(options = updatedBrandOptions),
            second = state.value.model.copy(options = updatedModelOptions)
        )
    }

    private fun preloadFuelType(fuelType: FuelType): SelectWrapper<FuelTypeMenuOption> {
        val updatedFuelTypeOptions = state.value.fuelType.options.map {
            if (it.fuelType == fuelType) {
                it.copy(checked = true)
            } else {
                it.copy(checked = false)
            }
        }
        return state.value.fuelType.copy(options = updatedFuelTypeOptions)
    }

    @OptIn(ExperimentalUuidApi::class)
    private fun buildVehicle(): Vehicle? {
        val currentState = state.value
        val year = currentState.year.selected ?: return null
        val brand = currentState.brand.selected ?: return null
        val model = currentState.model.selected ?: return null
        val fuelType = currentState.fuelType.selected ?: return null
        val uuid = currentState.uuid.ifEmpty { random().toString() }
        return Vehicle(
            uuid = uuid,
            year = year.value,
            brand = VehicleBrand(
                name = brand.vehicleBrandOption.name,
                model = model.model
            ),
            vin = "not-implemented",
            photo = "not-implemented",
            fuelType = fuelType.fuelType
        )
    }

    override suspend fun handleActions(action: VehicleDetailsAction) {
        when (action) {
            is VehicleDetailsAction.OnSelectYear -> selectYear(action.yearOption)
            is VehicleDetailsAction.OnSelectBrand -> selectBrandType(action.brandOption)
            is VehicleDetailsAction.OnSelectModel -> selectModel(action.modelOption)
            is VehicleDetailsAction.OnSelectFuelType -> selectFuelType(action.typeOption)
            is VehicleDetailsAction.OnVINValueChange -> {

            }

            is VehicleDetailsAction.OnSave -> save()
        }
    }
}