package com.example.fuelmanagementsystemwithfragments.ui.fuelexpense

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fuelmanagementsystemwithfragments.data.model.FuelExpenseCalculation
import com.example.fuelmanagementsystemwithfragments.data.repository.abstaraction.FuelRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FuelExpenseViewModel @Inject constructor(
    private val repository: FuelRepository
) : ViewModel() {
/*    var fuelPrice = 0f
    var vehicleAverage = 0f
    var distanceTravel = 0f*/
private val toastEventChannel = Channel<FuelExpenseScreenEvent>()
    val toastEvent = toastEventChannel.receiveAsFlow()

    fun saveExpenseData(
        fuelPriceEditText: String,
        vehicleAverageEditText: String,
        totalDistanceTravelEditText: String

    ) {
        if (fuelPriceEditText
                .isNotBlank() && vehicleAverageEditText
                .isNotBlank() && totalDistanceTravelEditText.isNotBlank()
        ) {
            calculateFuel(
                fuelPrice = fuelPriceEditText.toFloat(),
                distanceTravel = totalDistanceTravelEditText.toFloat(),
                vehicleAverage = vehicleAverageEditText.toFloat()
            )
        } else {
            viewModelScope.launch {
                toastEventChannel.send(FuelExpenseScreenEvent.ShowEmptyToast)
            }


//            Toast.makeText(appContext, "Please Fill all text First", Toast.LENGTH_LONG).show()
        }
    }
    var totalCost = MutableStateFlow(0f)

    private fun calculateFuel(vehicleAverage: Float, distanceTravel: Float, fuelPrice: Float) {
        val perLiter = (1f / vehicleAverage) * distanceTravel
        totalCost.value = perLiter * fuelPrice
        /*       Log.i("calculateFuel: ", "$perKm")
         Log.i("calculateFuel: ", "$perLiter")*/
        insertIntoDatabase(fuelPrice, vehicleAverage, distanceTravel)
    }

    private fun insertIntoDatabase(fuelPrice: Float, vehicleAverage: Float, distanceTravel: Float) {
        viewModelScope.launch {
            val fuelExpenseCalculation =
                FuelExpenseCalculation(fuelPrice, vehicleAverage, distanceTravel, totalCost.value)
            repository.upsert(fuelExpenseCalculation)
        }
    }
}
sealed class FuelExpenseScreenEvent{
    object ShowEmptyToast: FuelExpenseScreenEvent()
}