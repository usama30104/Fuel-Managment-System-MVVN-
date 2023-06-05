package com.example.fuelmanagementsystemwithfragments.data.dao
import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import com.example.fuelmanagementsystemwithfragments.data.model.FuelEfficiencyCalculation
import kotlinx.coroutines.flow.Flow
@Dao
interface FuelEfficiencyDao {
    @Upsert
    suspend fun upsert(FuelEfficiencyCalculation: FuelEfficiencyCalculation)
    @Query("SELECT * FROM FuelEfficiencyCalculation")
    fun getListOfFuelEfficiency(): Flow<List<FuelEfficiencyCalculation>>
}