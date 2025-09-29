package com.example.vehiclecompanion.datasource.database.vehicles.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.vehiclecompanion.datasource.database.vehicles.model.VehicleEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface VehicleDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertVehicle(vehicle: VehicleEntity): Long

    @Update
    suspend fun updateVehicle(vehicle: VehicleEntity): Int

    @Query("SELECT * FROM vehicle")
    fun getAllVehicles(): Flow<List<VehicleEntity>>

    @Query("SELECT * FROM vehicle WHERE uuid = :vehicleUuid LIMIT 1")
    suspend fun getVehicleByUuid(vehicleUuid: String): VehicleEntity?

    @Query("DELETE FROM vehicle")
    suspend fun deleteAll(): Int

    @Delete
    suspend fun delete(vehicle: VehicleEntity): Int
}
