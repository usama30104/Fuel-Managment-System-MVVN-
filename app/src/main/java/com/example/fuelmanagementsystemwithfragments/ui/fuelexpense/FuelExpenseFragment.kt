package com.example.fuelmanagementsystemwithfragments.ui.fuelexpense

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.fragment.app.viewModels
import com.example.fuelmanagementsystemwithfragments.R
import com.example.fuelmanagementsystemwithfragments.databinding.FragmentFuelExpenseBinding
import com.example.fuelmanagementsystemwithfragments.ui.baseclasses.BaseFragment
import com.example.fuelmanagementsystemwithfragments.utils.extension.launchWhenStarted
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest

@AndroidEntryPoint
class FuelExpenseFragment : BaseFragment<FragmentFuelExpenseBinding>() {

    private val fuelExpenseViewModel by viewModels<FuelExpenseViewModel>()
    override fun getFragmentView(): Int = R.layout.fragment_fuel_expense

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        /* val fuelPrice = binding.fuelPrice.editText
        val vehicleAveragePerKm = binding.vehicleAverage.editText
        val totalDistanceTravel = binding.totalDistanceTravell.editText*/

/*        fuelPrice?.addTextChangedListener {
            if (it.toString().isNotBlank())
                fuelExpenseViewModel.fuelPrice = it.toString().toFloat()
        }
        vehicleAveragePerKm?.addTextChangedListener {
            if (it.toString().isNotBlank())
                fuelExpenseViewModel.vehicleAverage = it.toString().toFloat()
        }
        totalDistanceTravel?.addTextChangedListener {
            if (it.toString().isNotBlank())
                fuelExpenseViewModel.distanceTravel = it.toString().toFloat()
        }*/
        binding!!.apply {
            fuelBtnCal.setOnClickListener {
                fuelExpenseViewModel.saveExpenseData(
                    fuelPriceEditText = fuelPriceEditText.text.toString(),
                    vehicleAverageEditText = vehicleAverageEditText.text.toString(),
                    totalDistanceTravelEditText = totalDistanceTravelEditText.text.toString()
                )
            }


            launchWhenStarted {
                Log.d("usama", "usma")

                fuelExpenseViewModel.toastEvent.collect { toastEvent ->
                    when (toastEvent) {
                        is FuelExpenseScreenEvent.ShowEmptyToast -> Toast.makeText(
                            requireContext(),
                            getString(R.string.empty),
                            Toast.LENGTH_SHORT
                        ).show()
                    }

                }

            }
            launchWhenStarted {
                fuelExpenseViewModel.totalCost.collectLatest { totalCost ->
                    textViewFuelExpense.text =
                        getString(R.string.total_cost_of_fuel_value, totalCost)
                }

            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}
