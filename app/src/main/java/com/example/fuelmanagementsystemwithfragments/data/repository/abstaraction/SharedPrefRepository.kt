package com.example.fuelmanagementsystemwithfragments.data.repository.abstaraction

interface SharedPrefRepository {
    fun getFuelInLiters():Float
    fun setFuelInLiters(value:Float)

    fun getInitialMeterReading():Float
    fun setInitialMeterReading(value:Float)

    fun getFuelPrice():Float
    fun setFuelPrice(value: Float)

    fun getIsFirstTime(): Boolean
    fun setIsFirstTime(b: Boolean)
}