package com.example.fuelmanagementsystemwithfragments.data.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import com.example.fuelmanagementsystemwithfragments.data.model.FuelExpenseCalculation
import kotlinx.coroutines.flow.Flow

@Dao
interface FuelManagementDao {
    @Upsert
    suspend fun upsert(fuelExpenseCalculation: FuelExpenseCalculation)
    @Query("SELECT * FROM FuelExpenseCalculation")
    fun getListOfFuelExpense(): Flow<List<FuelExpenseCalculation>>
}