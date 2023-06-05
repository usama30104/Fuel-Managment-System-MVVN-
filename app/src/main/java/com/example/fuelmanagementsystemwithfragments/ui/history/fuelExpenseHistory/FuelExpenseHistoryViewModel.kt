package com.example.fuelmanagementsystemwithfragments.ui.history.fuelExpenseHistory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fuelmanagementsystemwithfragments.data.model.FuelExpenseCalculation
import com.example.fuelmanagementsystemwithfragments.data.repository.abstaraction.FuelRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FuelExpenseHistoryViewModel @Inject constructor(private val repository: FuelRepository) :
    ViewModel() {

    val listHistory = MutableStateFlow<List<FuelExpenseCalculation>>(emptyList())

    init {
        viewModelScope.launch {
            repository.getListOfFuelExpense().collectLatest {list->
                listHistory.value = list

            }
        }
    }
}