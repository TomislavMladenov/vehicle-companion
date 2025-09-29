package com.example.vehiclecompanion.datasource.database.vehicles.model

import com.example.vehiclecompanion.core.model.FuelType
import com.example.vehiclecompanion.core.model.Vehicle
import com.example.vehiclecompanion.core.model.VehicleBrand
import com.example.vehiclecompanion.core.util.asEnumOrDefault

fun Vehicle.toEntity() = VehicleEntity(
    uuid = uuid,
    year = year,
    brand = brand.name,
    model = brand.model,
    fuelType = fuelType.name,
    vin = vin,
    photo = photo
)

fun VehicleEntity.toDomain() = Vehicle(
    uuid = uuid,
    year = year,
    brand = VehicleBrand(
        name = brand,
        model = model
    ),
    fuelType = fuelType.asEnumOrDefault(FuelType.GAS),
    vin = vin,
    photo = photo
)