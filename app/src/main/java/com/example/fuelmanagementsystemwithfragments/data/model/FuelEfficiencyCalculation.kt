package com.example.fuelmanagementsystemwithfragments.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class FuelEfficiencyCalculation(
    val initialFuel: Float,
    val initialReading: Float,
    val petrolPriceAtRefil: Float,
    val fuelUsed : Float,
    val finalReading:Float,
    val fuelPriceAtEnd: Float,
    val fuelDifference : Float,
    val result:String,
    @PrimaryKey(autoGenerate = true)
    val id:Int? = null
)
