package com.example.vehiclecompanion.datasource.database.vehicles.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "vehicle")
data class VehicleEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0L,
    val uuid: String,
    val year: String,
    val brand: String,
    val model: String,
    val fuelType: String,
    val vin: String,
    val photo: String
)