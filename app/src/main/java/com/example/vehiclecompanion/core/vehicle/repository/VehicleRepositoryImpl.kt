package com.example.vehiclecompanion.core.vehicle.repository

import com.example.vehiclecompanion.core.model.Vehicle
import com.example.vehiclecompanion.core.util.Constants.DefValue.INVALID_INT
import com.example.vehiclecompanion.core.util.Constants.DefValue.INVALID_LONG
import com.example.vehiclecompanion.core.util.unwrap
import com.example.vehiclecompanion.core.vehicle.datasource.VehicleOfflineDataSource


class VehicleRepositoryImpl(
    private val vehicleOfflineDataSource: VehicleOfflineDataSource,
) : VehicleRepository {

    override suspend fun save(vehicle: Vehicle) =
        vehicleOfflineDataSource.insert(vehicle).unwrap() ?: INVALID_LONG

    override suspend fun delete(vehicle: Vehicle) =
        vehicleOfflineDataSource.delete(vehicle).unwrap() ?: INVALID_INT

    override fun getAll() = vehicleOfflineDataSource.getAll().unwrap()

    override suspend fun getById(uuid: String) = vehicleOfflineDataSource.getById(uuid).unwrap()
}