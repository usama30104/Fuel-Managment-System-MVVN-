package com.example.fuelmanagementsystemwithfragments.ui.history.fuelEfficiencyHistory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fuelmanagementsystemwithfragments.data.model.FuelEfficiencyCalculation
import com.example.fuelmanagementsystemwithfragments.data.repository.abstaraction.FuelRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject
@HiltViewModel
class FuelEfficiencyHistoryViewModel @Inject constructor(private val repository: FuelRepository) :
    ViewModel() {
    val listEfficiencyHistory = MutableStateFlow<List<FuelEfficiencyCalculation>>(emptyList())

    init {
        viewModelScope.launch {
            repository.getListOfFuelEfficiency().collectLatest {list->
                listEfficiencyHistory.value = list

            }
        }
    }
}