package com.example.fuelmanagementsystemwithfragments.ui.home


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fuelmanagementsystemwithfragments.R
import com.example.fuelmanagementsystemwithfragments.ui.home.model.MainScreenItem
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

class MainScreenViewModel : ViewModel() {

    private val _mainScreenItemsClickEvent = Channel<MainScreenEvent>()
    val mainScreenItemsClickEvent = _mainScreenItemsClickEvent.receiveAsFlow()
    val mainScreenItemList = MutableStateFlow<List<MainScreenItem>>(listOf())

    init {
        initMainScreenItemsList()
    }

    private fun initMainScreenItemsList() {

        val list = mutableListOf<MainScreenItem>()
        list.add(
            MainScreenItem(
                nameResId = R.string.fuel_expense,
                imgResId = R.drawable.ic_wallet,
                action = R.id.action_mainScreenFragment_to_fuelExpenseFragment
            )
        )
        list.add(
            MainScreenItem(
                nameResId = R.string.fuel_efficiency,
                imgResId = R.drawable.ic_meter,
                action = R.id.action_mainScreenFragment_to_fuelEfficiencyFragment
            )
        )
        list.add(
            MainScreenItem(
                nameResId = R.string.history,
                imgResId = R.drawable.ic_history,
                action = R.id.action_mainScreenFragment_to_historyFragment
            )
        )

        mainScreenItemList.value = list
    }

    fun onMainScreenItemClick(action: Int) = viewModelScope.launch {
        _mainScreenItemsClickEvent.send(MainScreenEvent.Navigate(action))
    }
}

sealed class MainScreenEvent {
    class Navigate(val action: Int) : MainScreenEvent()
}