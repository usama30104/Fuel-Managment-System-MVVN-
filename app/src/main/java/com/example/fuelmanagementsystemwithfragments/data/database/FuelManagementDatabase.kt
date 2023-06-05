package com.example.fuelmanagementsystemwithfragments.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.fuelmanagementsystemwithfragments.data.dao.FuelEfficiencyDao
import com.example.fuelmanagementsystemwithfragments.data.dao.FuelManagementDao
import com.example.fuelmanagementsystemwithfragments.data.model.FuelEfficiencyCalculation
import com.example.fuelmanagementsystemwithfragments.data.model.FuelExpenseCalculation

@Database(
    entities = [FuelExpenseCalculation::class, FuelEfficiencyCalculation::class],
    version = 1
)
abstract class FuelManagementDatabase: RoomDatabase() {

        abstract fun getFuelEfficiencyDao(): FuelEfficiencyDao
        abstract fun getFuelManagementDao(): FuelManagementDao

}