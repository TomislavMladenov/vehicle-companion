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
            Pair(Constants.NavArgs.VEHICLE_ID, vehicleId)
        )
    )
    data object VehicleList : VehicleCompanionDestination("vehicleList")
//    sealed class Profile(
//        override val destination: String,
//        override val args: Map<String, String> = emptyMap()
//    ) : VehicleCompanionDestination("profile", args) {
//
//    }
//
//    sealed class Search(override val destination: String) : VehicleCompanionDestination("search", emptyMap()) {
//        data object ListView : Search("list")
//        data object Map : Search("map")
//    }

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

/**
 *  For demonstration will be kept in the same file but ideally we would like to separate them since each
 *  would be a navigation graph that will have it's own sub destinations and manage history / state
 *
 *  If we have multi module structure each should be kept in it's own module
 */



