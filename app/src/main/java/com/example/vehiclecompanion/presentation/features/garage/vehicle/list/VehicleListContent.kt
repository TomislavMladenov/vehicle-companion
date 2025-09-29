package com.example.vehiclecompanion.presentation.features.garage.vehicle.list

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import com.example.vehiclecompanion.R
import com.example.vehiclecompanion.core.model.Vehicle
import com.example.vehiclecompanion.presentation.features.garage.vehicle.list.mvi.VehicleListAction
import com.example.vehiclecompanion.presentation.features.garage.vehicle.list.mvi.VehicleListState
import com.example.vehiclecompanion.presentation.ui.components.loading.FullScreenCircularLoading
import com.example.vehiclecompanion.presentation.ui.model.ResourceString
import com.example.vehiclecompanion.presentation.ui.model.textResource
import com.example.vehiclecompanion.presentation.ui.theme.Dimens

@Composable
internal fun VehicleListContent(
    state: VehicleListState,
    action: (VehicleListAction) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(Dimens.space_1),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        if (!state.initiallyLoaded) {
            FullScreenCircularLoading()
        } else {
            Box(
                modifier = Modifier.fillMaxSize()
            ) {
                VehicleListView(state.vehicles, action)

                AddNewVehicleView { action(VehicleListAction.OnNewVehicle) }

                NoVehiclesFoundView(state.vehicles)
            }
        }
    }
}


@Composable
private fun VehicleListView(
    vehicles: List<Vehicle>,
    action: (VehicleListAction) -> Unit
) {
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(Dimens.space_2)
    ) {
        items(vehicles) { vehicle ->
            VehicleCardView(vehicle) {
                action(VehicleListAction.OnSelectVehicle(vehicle))
            }
        }
    }
}

@Composable
private fun VehicleCardView(vehicle: Vehicle, onClick: () -> Unit) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        onClick = onClick
    ) {
        Column(
            modifier = Modifier.fillMaxWidth().padding(Dimens.space_0_5),
            verticalArrangement = Arrangement.spacedBy(Dimens.space_1)
        ) {
            Text(vehicle.name)
            Text("${vehicle.year} - ${vehicle.vin}")
        }
    }
}

@Composable
private fun BoxScope.AddNewVehicleView(onClick: () -> Unit) {
    FloatingActionButton(
        modifier = Modifier.align(Alignment.BottomEnd).padding(Dimens.space_2),
        onClick = onClick
    ) {
        Text("+")
    }
}

@Composable
private fun BoxScope.NoVehiclesFoundView(vehicles: List<Vehicle>) {
    if (vehicles.isEmpty()) {
        Text(
            textResource(ResourceString(R.string.tv_vehicle_list_empty_state)),
            textAlign = TextAlign.Center,
            modifier = Modifier.align(Alignment.Center)
        )
    }
}