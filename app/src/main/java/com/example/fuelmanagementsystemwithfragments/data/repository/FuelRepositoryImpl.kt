package com.example.fuelmanagementsystemwithfragments.data.repository

import android.content.SharedPreferences
import com.example.fuelmanagementsystemwithfragments.data.dao.FuelEfficiencyDao
import com.example.fuelmanagementsystemwithfragments.data.dao.FuelManagementDao
import com.example.fuelmanagementsystemwithfragments.data.model.FuelEfficiencyCalculation
import com.example.fuelmanagementsystemwithfragments.data.model.FuelExpenseCalculation
import com.example.fuelmanagementsystemwithfragments.data.repository.abstaraction.FuelRepository
import com.example.fuelmanagementsystemwithfragments.data.repository.abstaraction.SharedPrefRepository
import com.example.fuelmanagementsystemwithfragments.utils.Constants
import kotlinx.coroutines.flow.Flow


class FuelRepositoryImpl(
    private val fuelEfficiencyDao: FuelEfficiencyDao,
    private val fuelManagementDao: FuelManagementDao,
    private val sharedPreferences: SharedPreferences
) : FuelRepository, SharedPrefRepository {

    override suspend fun upsert(fuelEfficiencyCalculation: FuelEfficiencyCalculation) {
        fuelEfficiencyDao.upsert(fuelEfficiencyCalculation)
    }

    override suspend fun upsert(fuelExpenseCalculation: FuelExpenseCalculation) {
        fuelManagementDao.upsert(fuelExpenseCalculation)
    }

    override fun getListOfFuelEfficiency(): Flow<List<FuelEfficiencyCalculation>> {
        return fuelEfficiencyDao.getListOfFuelEfficiency()
    }

    override fun getListOfFuelExpense(): Flow<List<FuelExpenseCalculation>> {
        return fuelManagementDao.getListOfFuelExpense()
    }

    override fun getFuelInLiters(): Float {
        return sharedPreferences.getFloat(Constants.FUEL_IN_LITERS, 0f)
    }

    override fun setFuelInLiters(value: Float) {
        sharedPreferences.edit().putFloat(Constants.FUEL_IN_LITERS, value).apply()
    }

    override fun getInitialMeterReading(): Float {
        return sharedPreferences.getFloat(Constants.INITIAL_READING, 0f)
    }

    override fun setInitialMeterReading(value: Float) {
        sharedPreferences.edit().putFloat(Constants.INITIAL_READING, value).apply()
    }

    override fun getFuelPrice(): Float {
        return sharedPreferences.getFloat(Constants.FUEL_PRICE, 0f)
    }

    override fun setFuelPrice(value: Float) {
        sharedPreferences.edit().putFloat(Constants.FUEL_PRICE, value).apply()
    }

    override fun getIsFirstTime(): Boolean {
        return sharedPreferences.getBoolean(Constants.IS_FIRST_TIME, true)
    }

    override fun setIsFirstTime(b: Boolean) {
        sharedPreferences.edit().putBoolean(Constants.IS_FIRST_TIME, b).apply()
    }
}