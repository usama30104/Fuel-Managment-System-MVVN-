package com.example.fuelmanagementsystemwithfragments.ui.history.fuelExpenseHistory

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.example.fuelmanagementsystemwithfragments.R
import com.example.fuelmanagementsystemwithfragments.databinding.FragmentFuelExpenseHistoryBinding
import com.example.fuelmanagementsystemwithfragments.ui.history.fuelExpenseHistory.adapter.FuelExpenseHistoryAdapter
import com.example.fuelmanagementsystemwithfragments.utils.extension.launchWhenStarted
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class FuelExpenseHistoryFragment : Fragment(R.layout.fragment_fuel_expense_history) {

    private val fuelExpenseHistoryViewModel by viewModels<FuelExpenseHistoryViewModel>()

    private var _binding: FragmentFuelExpenseHistoryBinding? = null
    private val binding get() = _binding!!

    private val adapter by lazy { FuelExpenseHistoryAdapter() }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentFuelExpenseHistoryBinding.bind(view)

        binding.recycleView.adapter = adapter

        launchWhenStarted {
            fuelExpenseHistoryViewModel.listHistory.collectLatest {list->

                adapter.submitList(list)

            }
        }
/*        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                fuelExpenseHistoryViewModel.listHistory.collectLatest {

                    adapter.submitList(it)

                }
            }
        }*/
    }
}