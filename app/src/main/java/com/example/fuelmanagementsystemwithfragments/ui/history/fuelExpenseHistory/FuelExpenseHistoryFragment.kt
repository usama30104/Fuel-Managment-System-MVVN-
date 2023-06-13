package com.example.fuelmanagementsystemwithfragments.ui.history.fuelExpenseHistory

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.example.fuelmanagementsystemwithfragments.R
import com.example.fuelmanagementsystemwithfragments.databinding.FragmentFuelExpenseHistoryBinding
import com.example.fuelmanagementsystemwithfragments.ui.baseclasses.BaseFragment
import com.example.fuelmanagementsystemwithfragments.ui.history.fuelExpenseHistory.adapter.FuelExpenseHistoryAdapter
import com.example.fuelmanagementsystemwithfragments.utils.extension.launchWhenStarted
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest

@AndroidEntryPoint
class FuelExpenseHistoryFragment : BaseFragment<FragmentFuelExpenseHistoryBinding>() {

    private val fuelExpenseHistoryViewModel by viewModels<FuelExpenseHistoryViewModel>()

    private val adapter by lazy { FuelExpenseHistoryAdapter() }
    override fun getFragmentView(): Int = R.layout.fragment_fuel_expense_history

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        binding!!.recycleView.adapter = adapter

        launchWhenStarted {
            fuelExpenseHistoryViewModel.listHistory.collectLatest { list ->

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

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}