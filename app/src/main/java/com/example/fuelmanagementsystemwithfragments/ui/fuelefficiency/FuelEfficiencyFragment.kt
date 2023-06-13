package com.example.fuelmanagementsystemwithfragments.ui.fuelefficiency

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.fuelmanagementsystemwithfragments.R
import com.example.fuelmanagementsystemwithfragments.databinding.FragmentFuelEfficiencyBinding
import com.example.fuelmanagementsystemwithfragments.ui.baseclasses.BaseFragment
import com.example.fuelmanagementsystemwithfragments.utils.extension.launchWhenStarted
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest

@AndroidEntryPoint
class FuelEfficiencyFragment : BaseFragment<FragmentFuelEfficiencyBinding>() {
    override fun getFragmentView(): Int = R.layout.fragment_fuel_efficiency


    private val fuelEfficiencyViewModel by viewModels<FuelEfficiencyViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        launchWhenStarted {
            fuelEfficiencyViewModel.fuelEfficiencyEvent.collect { event ->
                when (event) {
                    FuelEfficiencyViewModel.FuelEfficiencyScreenEvent.ShowEmptyToast -> {
                        Toast.makeText(
                            requireContext(),
                            getString(R.string.empty),
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }

            }
        }
        /* viewLifecycleOwner.lifecycleScope.launch {
             repeatOnLifecycle(Lifecycle.State.STARTED){
                 fuelEfficiencyViewModel.fuelEfficiencyEvent.collect{
                     Toast.makeText(requireContext(), it, Toast.LENGTH_SHORT).show()
                 }
             }
         }*/

        binding!!.apply {
            launchWhenStarted {
                fuelEfficiencyViewModel.isFirstTime.collectLatest { isFirstTime ->
                    if (isFirstTime) {
                        totalLitersOfFuel.hint = getString(R.string.total_liters_of_fuel)
                        meterReading.hint = getString(R.string.initial_reading)
                        fuelPrice.hint = getString(R.string.furl_price_at_start)
                        fuelBtnCal.text = getString(R.string.save)
                    } else {
                        totalLitersOfFuel.hint = getString(R.string.fuel_used)
                        meterReading.hint = getString(R.string.final_reading)
                        fuelPrice.hint = getString(R.string.fuel_price_at_refill)
                        fuelBtnCal.text = getString(R.string.calculate_Button)
                    }
                }
            }

            launchWhenStarted {
                fuelEfficiencyViewModel.result.collectLatest { result ->
                    binding!!.textViewFuelEfficiency.text = result
                }

            }
            /*    viewLifecycleOwner.lifecycleScope.launch {
                    repeatOnLifecycle(Lifecycle.State.STARTED) {
                        fuelEfficiencyViewModel.result.collectLatest {result->
                            binding.textViewFuelEfficiency.text = result
                        }
                    }
                }
    */
            fuelBtnCal.setOnClickListener {
                fuelEfficiencyViewModel.saveFuelData(
                    totalLitersOfFuel = totalLitersOfFuelEditText.text.toString(),
                    meterReading = meterReadingEditText.text.toString(),
                    fuelPrice = fuelPriceEditText.text.toString(),
                ) {
                    findNavController().navigateUp()
                }
            }
            /*if (fuelPriceEditText.text.toString()
                    .isNotBlank() && meterReadingEditText.text.toString()
                    .isNotBlank() && fuelPriceEditText.text.toString().isNotBlank()
            ) {
                if (check) {
                    sharedPref.edit()
                        .putFloat("fuelInLiters", fuelPriceEditText.text.toString().toFloat())
                        .apply()
                    sharedPref.edit()
                        .putFloat(
                            "initialReading",
                            meterReadingEditText.text.toString().toFloat()
                        )
                        .apply()
                    sharedPref.edit()
                        .putFloat("fuelPrice", fuelPriceEditText.text.toString().toFloat())
                        .apply()
//                    Toast.makeText(this, "Your data is Saved", Toast.LENGTH_LONG).show()
                    findNavController().popBackStack()
                } else {
//                  fuelEfficiencyViewModel.fuelUsed = sharedPref.getFloat("fuelInLiters",0f)
                    val initMeterReading = sharedPref.getFloat("initialReading", 0f)
                    val initialFuelPrice = sharedPref.getFloat("fuelPrice", 0f)
                    fuelEfficiencyViewModel.efficiencyHealth(
                        initialReading = initMeterReading,
                        finalReading = meterReadingEditText.text.toString().toFloat(),
                        usedFuel = totalLitersOfFuelEditText.text.toString().toFloat()
                    )
                    fuelEfficiencyViewModel.efficiencyInsertIntoDatabase(
                        initialFuel = initMeterReading,
                        initialReading = initMeterReading,
                        petrolPriceAtRefill = fuelPriceEditText.text.toString().toFloat(),
                        finalReading = meterReadingEditText.text.toString().toFloat(),
                        initialFuelPrice = initialFuelPrice
                    )
                }

                sharedPref.edit().putBoolean("flag", !check).apply()
            } else {
                Toast.makeText(
                    requireContext(),
                    "Please Fill all text First",
                    Toast.LENGTH_LONG
                ).show()
            }*/
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}