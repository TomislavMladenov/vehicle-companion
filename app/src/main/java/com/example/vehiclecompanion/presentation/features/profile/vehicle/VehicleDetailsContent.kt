package com.example.vehiclecompanion.presentation.features.profile.vehicle

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.example.vehiclecompanion.R
import com.example.vehiclecompanion.presentation.features.profile.vehicle.details.mvi.VehicleDetailsAction
import com.example.vehiclecompanion.presentation.features.profile.vehicle.details.mvi.VehicleDetailsState
import com.example.vehiclecompanion.presentation.ui.components.menu.BasicModalMenuOptions
import com.example.vehiclecompanion.presentation.ui.theme.Dimens

@Composable
fun VehicleDetailsContent(
    state: VehicleDetailsState,
    action: (VehicleDetailsAction) -> Unit
) {
    Column(
        modifier = Modifier.fillMaxSize().padding(Dimens.space_1)
    ) {
        BasicModalMenuOptions(
            label = stringResource(R.string.tv_fuel_type_label),
            items = state.fuelType.options,
            onItemSelected = {
                action(VehicleDetailsAction.OnSelectFuelType(it))
            }
        )
        Spacer(modifier = Modifier.height(Dimens.space_2))
    }
}