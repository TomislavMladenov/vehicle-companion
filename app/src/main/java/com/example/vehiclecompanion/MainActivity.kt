package com.example.vehiclecompanion

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.vehiclecompanion.presentation.features.garage.vehicle.details.VehicleDetailsScreen
import com.example.vehiclecompanion.presentation.features.garage.vehicle.list.VehicleListScreen
import com.example.vehiclecompanion.presentation.navigation.NavigationCommand
import com.example.vehiclecompanion.presentation.navigation.NavigationManager
import com.example.vehiclecompanion.presentation.navigation.VehicleCompanionDestination
import com.example.vehiclecompanion.presentation.ui.components.topbar.VehicleAppTopbar
import com.example.vehiclecompanion.presentation.ui.theme.VehicleCompanionTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import timber.log.Timber
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var navigationManager: NavigationManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            VehicleCompanionTheme {
                RootComposable(navigationManager)
            }
        }
    }
}

@Composable
private fun RootComposable(navigationManager: NavigationManager) {
    val navController = rememberNavController()

    Scaffold(
        topBar = {
            VehicleAppTopbar(navController) { navController.navigateUp() }
        }
    ) { paddingValues ->
        NavHost(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
            navController = navController,
            startDestination = VehicleCompanionDestination.VehicleDetails(vehicleId = "test").routePath
        ) {
            composable(
                route = VehicleCompanionDestination.VehicleDetails().route,
                arguments = VehicleCompanionDestination.VehicleDetails().arguments
            ) {
                VehicleDetailsScreen()
            }
            composable(route = VehicleCompanionDestination.VehicleList.route,) {
                VehicleListScreen()
            }
        }
    }
    SubscribeToNavEvents(navController, navigationManager)
}

@Composable
private fun SubscribeToNavEvents(
    navHostController: NavHostController,
    navigationManager: NavigationManager
) {
    val lifecycle = androidx.lifecycle.compose.LocalLifecycleOwner.current
    LaunchedEffect(Unit) {
        lifecycle.repeatOnLifecycle(Lifecycle.State.RESUMED) {
            navigationManager.navActions.collectLatest { navigationCommand ->
                navigate(navigationCommand, navHostController)
            }
        }
    }
}

private fun navigate(navigationCommand: NavigationCommand, navHostController: NavHostController) {
    Timber.i("$navigationCommand")
    when (navigationCommand) {
        NavigationCommand.Back -> {
            Timber.i("on back triggered.")
            navHostController.navigateUp()
        }

        is NavigationCommand.Navigate -> {
            val currentDestination = navHostController.currentDestination?.route
            Timber.i("current = $currentDestination, new = ${navigationCommand.destination.route}")
            Timber.i("changed to $navigationCommand")
            // Handle in NavHost
            navHostController.navigate(
                route = navigationCommand.destination.routePath
            )
        }
    }
}