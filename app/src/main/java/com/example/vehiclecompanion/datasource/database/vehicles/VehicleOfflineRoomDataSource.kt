package com.example.vehiclecompanion.datasource.database.vehicles

import com.example.vehiclecompanion.core.model.Vehicle
import com.example.vehiclecompanion.core.util.safeCall
import com.example.vehiclecompanion.core.util.safeSuspendCall
import com.example.vehiclecompanion.core.vehicle.datasource.VehicleOfflineDataSource
import com.example.vehiclecompanion.datasource.database.vehicles.dao.VehicleDao
import com.example.vehiclecompanion.datasource.database.vehicles.model.toDomain
import com.example.vehiclecompanion.datasource.database.vehicles.model.toEntity
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.map

class VehicleOfflineRoomDataSource(
    private val dao: VehicleDao
) : VehicleOfflineDataSource {

    override suspend fun insert(vehicle: Vehicle) =
        safeSuspendCall { dao.insertVehicle(vehicle.toEntity()) }

    override fun getAll() = safeCall {
        dao.getAllVehicles().distinctUntilChanged().map { list ->
            list.map { it.toDomain() }
        }
    }

    override suspend fun getById(uuid: String) = safeSuspendCall {
        dao.getVehicleByUuid(uuid)?.toDomain()
    }

    override suspend fun delete(vehicle: Vehicle) =
        safeSuspendCall { dao.delete(vehicle.toEntity()) }

}