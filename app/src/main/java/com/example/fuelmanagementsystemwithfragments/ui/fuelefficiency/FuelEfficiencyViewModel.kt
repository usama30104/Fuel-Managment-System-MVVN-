package com.example.fuelmanagementsystemwithfragments.ui.fuelefficiency

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fuelmanagementsystemwithfragments.data.model.FuelEfficiencyCalculation
import com.example.fuelmanagementsystemwithfragments.data.repository.abstaraction.FuelRepository
import com.example.fuelmanagementsystemwithfragments.data.repository.abstaraction.SharedPrefRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FuelEfficiencyViewModel @Inject constructor(
    private val repository: FuelRepository,
    private val sharedPrefRepository: SharedPrefRepository
) : ViewModel() {

    val isFirstTime = MutableStateFlow(sharedPrefRepository.getIsFirstTime())
    private val fuelEfficiencyEventChannel = Channel<FuelEfficiencyScreenEvent>()
    val fuelEfficiencyEvent = fuelEfficiencyEventChannel.receiveAsFlow()

    fun saveFuelData(
        totalLitersOfFuel: String,
        meterReading: String,
        fuelPrice: String,
        onNavigationUp: () -> Unit,
    ) {
        if (totalLitersOfFuel.isNotBlank() && meterReading.isNotBlank() && fuelPrice.isNotBlank()) {
            if (isFirstTime.value) {
                sharedPrefRepository.setFuelInLiters(totalLitersOfFuel.toFloat())
                /*  sharedPref.edit()
                      .putFloat(INITIAL_READING, meterReading.toFloat())
                      .apply()*/
                sharedPrefRepository.setInitialMeterReading(meterReading.toFloat())

                /*   sharedPref.edit()
                       .putFloat(FUEL_PRICE, fuelPrice.toFloat())
                       .apply()*/
                sharedPrefRepository.setFuelPrice(fuelPrice.toFloat())
                onNavigationUp.invoke()
            } else {
                val initMeterReading = sharedPrefRepository.getInitialMeterReading()
                val initialFuelPrice = sharedPrefRepository.getFuelPrice()
                efficiencyHealth(
                    initialReading = initMeterReading,
                    finalReading = meterReading.toFloat(),
                    usedFuel = totalLitersOfFuel.toFloat()
                )
                efficiencyInsertIntoDatabase(
                    initialFuel = initMeterReading,
                    initialReading = initMeterReading,
                    petrolPriceAtRefill = fuelPrice.toFloat(),
                    finalReading = meterReading.toFloat(),
                    initialFuelPrice = initialFuelPrice
                )
            }

            sharedPrefRepository.setIsFirstTime(!isFirstTime.value)
        } else {
            viewModelScope.launch {
                fuelEfficiencyEventChannel.send(FuelEfficiencyScreenEvent.ShowEmptyToast)
            }

        }
    }

    /*        var initialFuel = 0f
        var initialReading = 0f
        var petrolPriceAtRefill = 0f
        var fuelDifference = 0f
        var finalReading = 0f
        var fuelPriceAtEnd = 0f
        var readingDifference = 0f*/
    var result = MutableStateFlow("")
    private fun efficiencyHealth(initialReading: Float, finalReading: Float, usedFuel: Float) {
        val readingDifference = finalReading - initialReading
        result.value = "Average of your vehicle is : ${readingDifference / usedFuel}"
/*
        Log.i( "efficiencyHealth: ", "readingDifference: $readingDifference")
        Log.i( "efficiencyHealth: ", "readingDifference: $readingDifference") //result.value = "Average of your car is : ${readingDifference / readingDifference}"
        Log.i( "efficiencyHealth: ", "result: $result") //   val totalAverage = petrolInLiters * initialReading
*/
    }

    private fun efficiencyInsertIntoDatabase(
        initialFuel: Float,
        initialReading: Float,
        petrolPriceAtRefill: Float,
        finalReading: Float,
        initialFuelPrice: Float
    ) {
        val fuelDifference = petrolPriceAtRefill - initialFuelPrice
        viewModelScope.launch {
            val fuelEfficiencyCalculation = FuelEfficiencyCalculation(
                // fuelPrice, initialReading, fuelPrice, result.value!!
                initialFuel = initialFuel,
                initialReading = initialReading,
                petrolPriceAtRefil = petrolPriceAtRefill,
                fuelUsed = initialFuel,
                finalReading = finalReading,
                fuelPriceAtEnd = initialFuelPrice,
                result = result.value,
                fuelDifference = fuelDifference
            )
            repository.upsert(fuelEfficiencyCalculation)
        }
    }
}

sealed class FuelEfficiencyScreenEvent {
    object ShowEmptyToast : FuelEfficiencyScreenEvent()
}