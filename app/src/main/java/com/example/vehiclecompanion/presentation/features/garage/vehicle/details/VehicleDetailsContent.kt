package com.example.vehiclecompanion.presentation.features.garage.vehicle.details

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.example.vehiclecompanion.R
import com.example.vehiclecompanion.presentation.features.garage.vehicle.details.mvi.VehicleDetailsAction
import com.example.vehiclecompanion.presentation.features.garage.vehicle.details.mvi.VehicleDetailsState
import com.example.vehiclecompanion.presentation.ui.components.atoms.button.RoundedButton
import com.example.vehiclecompanion.presentation.ui.components.atoms.spacer.DefaultSpacing
import com.example.vehiclecompanion.presentation.ui.components.atoms.spacer.FillAvailableHeightSpacer
import com.example.vehiclecompanion.presentation.ui.components.atoms.text.VCScreenTitle
import com.example.vehiclecompanion.presentation.ui.components.menu.BasicModalMenuOptions
import com.example.vehiclecompanion.presentation.ui.model.ResourceString
import com.example.vehiclecompanion.presentation.ui.model.textResource
import com.example.vehiclecompanion.presentation.ui.model.textResourceOrEmpty
import com.example.vehiclecompanion.presentation.ui.theme.Dimens

@Composable
fun VehicleDetailsContent(
    state: VehicleDetailsState,
    action: (VehicleDetailsAction) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(Dimens.space_1)
    ) {
        VehicleName(state)

        BasicModalMenuOptions(
            label = stringResource(R.string.tv_year_label),
            items = state.year.options,
            onItemSelected = {
                action(VehicleDetailsAction.OnSelectYear(it))
            }
        )
        DefaultSpacing()

        BasicModalMenuOptions(
            label = stringResource(R.string.tv_brand_label),
            items = state.brand.options,
            onItemSelected = {
                action(VehicleDetailsAction.OnSelectBrand(it))
            }
        )
        DefaultSpacing()

        BasicModalMenuOptions(
            label = stringResource(R.string.tv_model_label),
            items = state.model.options,
            enabled = state.model.canInteract,
            onItemSelected = {
                action(VehicleDetailsAction.OnSelectModel(it))
            }
        )
        DefaultSpacing()

        BasicModalMenuOptions(
            label = stringResource(R.string.tv_fuel_type_label),
            items = state.fuelType.options,
            onItemSelected = {
                action(VehicleDetailsAction.OnSelectFuelType(it))
            }
        )

        SaveButtonView(state.isSaveEnabled) {
            action(VehicleDetailsAction.OnSave)
        }
    }
}

@Composable
private fun ColumnScope.SaveButtonView(
    enabled: Boolean,
    onClick: () -> Unit
) {
    FillAvailableHeightSpacer()
    Row(
        Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        RoundedButton(
            text = textResource(ResourceString(R.string.tv_save)),
            onClick = onClick,
            enabled = enabled
        )
    }
    DefaultSpacing()
}

@Composable
private fun VehicleName(state: VehicleDetailsState) {
    state.name.first?.let {
        VCScreenTitle(
            title = "${textResource(it)} ${textResourceOrEmpty(state.name.second)}"
        )
        DefaultSpacing()
    }
}