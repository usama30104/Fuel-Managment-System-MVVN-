package com.example.fuelmanagementsystemwithfragments.ui.history.fuelEfficiencyHistory

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.fuelmanagementsystemwithfragments.R
import com.example.fuelmanagementsystemwithfragments.databinding.FragmentFuelExpenseHistoryBinding
import com.example.fuelmanagementsystemwithfragments.ui.history.fuelEfficiencyHistory.adapter.FueLEfficiencyHistoryAdapter
import com.example.fuelmanagementsystemwithfragments.utils.extension.launchWhenStarted
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest

@AndroidEntryPoint
class FuelEfficiencyHistoryFragment : Fragment(R.layout.fragment_fuel_efficiency_history){
    private var _binding: FragmentFuelExpenseHistoryBinding? = null
    private val binding get() = _binding!!

    private val fuelEfficiencyHistoryViewModel by viewModels<FuelEfficiencyHistoryViewModel>()
    private val adapterEfficiency by lazy { FueLEfficiencyHistoryAdapter() }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.recycleView.adapter = adapterEfficiency

        launchWhenStarted {
            fuelEfficiencyHistoryViewModel.listEfficiencyHistory.collectLatest { list ->
                adapterEfficiency.submitList(list)
            }
        }

        /* viewLifecycleOwner.lifecycleScope.launch {
             repeatOnLifecycle(Lifecycle.State.STARTED) {
                 fuelEfficiencyHistoryViewModel.listEfficiencyHistory.collectLatest {

                     adapterEfficiency.submitList(it)

                 }
             }
         }*/

    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}