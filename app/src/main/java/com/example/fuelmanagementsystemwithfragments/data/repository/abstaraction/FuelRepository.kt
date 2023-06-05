package com.example.fuelmanagementsystemwithfragments.data.repository.abstaraction

import com.example.fuelmanagementsystemwithfragments.data.model.FuelEfficiencyCalculation
import com.example.fuelmanagementsystemwithfragments.data.model.FuelExpenseCalculation
import kotlinx.coroutines.flow.Flow

interface FuelRepository {
    suspend fun upsert(fuelEfficiencyCalculation: FuelEfficiencyCalculation)

    fun getListOfFuelEfficiency(): Flow<List<FuelEfficiencyCalculation>>

    suspend fun upsert(fuelExpenseCalculation: FuelExpenseCalculation)

    fun getListOfFuelExpense(): Flow<List<FuelExpenseCalculation>>

}