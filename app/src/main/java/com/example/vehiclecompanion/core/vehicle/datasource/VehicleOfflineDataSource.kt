package com.example.vehiclecompanion.core.vehicle.datasource

import com.example.vehiclecompanion.core.model.Vehicle
import kotlinx.coroutines.flow.Flow

interface VehicleOfflineDataSource {

    suspend fun save(vehicle: Vehicle): Result<Long>

    suspend fun delete(vehicle: Vehicle): Result<Int>

    fun getAll(): Result<Flow<List<Vehicle>>>

    suspend fun getById(uuid: String): Result<Vehicle?>
}