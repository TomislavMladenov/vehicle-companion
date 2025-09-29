package com.example.vehiclecompanion.datasource.database.di

import android.content.Context
import androidx.room.Room
import com.example.vehiclecompanion.core.vehicle.datasource.VehicleOfflineDataSource
import com.example.vehiclecompanion.datasource.database.VehicleCompanionDatabase
import com.example.vehiclecompanion.datasource.database.util.DatabaseConst
import com.example.vehiclecompanion.datasource.database.vehicles.VehicleOfflineRoomDataSource
import com.example.vehiclecompanion.datasource.database.vehicles.dao.VehicleDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context) = Room.databaseBuilder(
        context, VehicleCompanionDatabase::class.java, DatabaseConst.VC_DATABASE
    )
        .fallbackToDestructiveMigration(false)
        .build()

    @Provides
    @Singleton
    fun provideVehicleDao(db: VehicleCompanionDatabase) = db.vehicleDao()

    @Provides
    @Singleton
    fun provideVehicleOfflineDataSource(dao: VehicleDao): VehicleOfflineDataSource =
        VehicleOfflineRoomDataSource(dao)
}