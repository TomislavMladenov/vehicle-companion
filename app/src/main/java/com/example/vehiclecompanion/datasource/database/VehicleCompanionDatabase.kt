package com.example.vehiclecompanion.datasource.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.vehiclecompanion.datasource.database.vehicles.dao.VehicleDao
import com.example.vehiclecompanion.datasource.database.vehicles.model.VehicleEntity

@Database(entities = [VehicleEntity::class], version = 1)
abstract class VehicleCompanionDatabase : RoomDatabase() {

    abstract fun vehicleDao(): VehicleDao

}