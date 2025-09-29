package com.example.vehiclecompanion.presentation.navigation

import androidx.navigation.NamedNavArgument
import androidx.navigation.NavType
import androidx.navigation.navArgument
import com.example.vehiclecompanion.core.util.Constants

sealed class VehicleCompanionDestination(
    open val destination: String,
    open val args: Map<String, String> = emptyMap()
) {
    data class VehicleDetails(
        val vehicleId: String = ""
    ) : VehicleCompanionDestination(
        destination = "vehicleDetails",
        args = mapOf(
            Pair(Constants.NavArgs.VEHICLE_UUID, vehicleId)
        )
    )
    data object VehicleList : VehicleCompanionDestination("vehicleList")

    data object ListView : VehicleCompanionDestination("searchListView")
    data object MapView : VehicleCompanionDestination("searchMapView")

    val arguments: List<NamedNavArgument> = if (args.isNotEmpty()) {
        buildList {
            args.forEach { element ->
                add(navArgument(element.key) {
                    type = NavType.StringType
                })
            }
        }
    } else emptyList()

    val route: String
        get() = buildString {
            append(destination)
            args.forEach { argument ->
                append("/${argument.key}/{${argument.key}}")
            }
        }

    val routePath: String
        get() = buildString {
            append(destination)
            args.forEach { argument ->
                append("/${argument.key}/${argument.value}")
            }
        }
}
