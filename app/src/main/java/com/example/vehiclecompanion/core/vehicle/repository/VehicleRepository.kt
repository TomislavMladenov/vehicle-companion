package com.example.vehiclecompanion.core.vehicle.repository

import com.example.vehiclecompanion.core.model.Vehicle
import kotlinx.coroutines.flow.Flow

interface VehicleRepository {

    suspend fun save(vehicle: Vehicle): Long

    suspend fun delete(vehicle: Vehicle): Int

    fun getAll(): Flow<List<Vehicle>>

    suspend fun getById(uuid: String): Vehicle?
}