package com.example.fuelmanagementsystemwithfragments.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class FuelExpenseCalculation(
    val fuelPrice: Float,
    val VehicleAverage: Float,
    val totalDistance: Float,
    val totalCalculation: Float,
    @PrimaryKey(autoGenerate = true)
    val id: Int? = null
)
