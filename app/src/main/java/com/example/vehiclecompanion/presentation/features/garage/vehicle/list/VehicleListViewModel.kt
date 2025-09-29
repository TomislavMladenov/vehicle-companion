package com.example.vehiclecompanion.presentation.features.garage.vehicle.list

import androidx.lifecycle.viewModelScope
import com.example.vehiclecompanion.core.vehicle.repository.VehicleRepository
import com.example.vehiclecompanion.presentation.base.BaseViewModel
import com.example.vehiclecompanion.presentation.features.garage.vehicle.list.mvi.VehicleListAction
import com.example.vehiclecompanion.presentation.features.garage.vehicle.list.mvi.VehicleListState
import com.example.vehiclecompanion.presentation.navigation.NavigationCommand
import com.example.vehiclecompanion.presentation.navigation.NavigationManager
import com.example.vehiclecompanion.presentation.navigation.VehicleCompanionDestination
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class VehicleListViewModel
@Inject constructor(
    private val navigationManager: NavigationManager,
    private val vehicleRepository: VehicleRepository
) : BaseViewModel<VehicleListState, VehicleListAction>(VehicleListState()) {

    private fun getVehicles() {
        vehicleRepository.getAll().onEach { list ->
            updateState { copy(vehicles = list, initiallyLoaded = true) }
        }.launchIn(viewModelScope)
    }

    private suspend fun openVehicleDetails(uuid: String?) {
        navigationManager.navigate(
            NavigationCommand.Navigate(
                VehicleCompanionDestination.VehicleDetails(uuid ?: "")
            )
        )
    }

    override suspend fun handleActions(action: VehicleListAction) {
        when (action) {
            VehicleListAction.GetVehicles -> getVehicles()
            VehicleListAction.OnNewVehicle -> openVehicleDetails(null)
            is VehicleListAction.OnSelectVehicle -> openVehicleDetails(action.vehicle.uuid)
        }
    }
}