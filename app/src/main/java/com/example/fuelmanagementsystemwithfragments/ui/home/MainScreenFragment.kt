package com.example.fuelmanagementsystemwithfragments.ui.home

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.fuelmanagementsystemwithfragments.R
import com.example.fuelmanagementsystemwithfragments.databinding.FragmentMainScreenBinding
import com.example.fuelmanagementsystemwithfragments.ui.home.adapter.MainScreenAdapter
import com.example.fuelmanagementsystemwithfragments.ui.home.model.MainScreenItem
import com.example.fuelmanagementsystemwithfragments.utils.extension.launchWhenStarted
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest

@AndroidEntryPoint
class MainScreenFragment : Fragment(R.layout.fragment_main_screen) {
    private val mainScreenViewModel by viewModels<MainScreenViewModel>()
    private var _binding: FragmentMainScreenBinding? = null
    private val binding get() = _binding!!
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentMainScreenBinding.bind(view)
        launchWhenStarted {
            mainScreenViewModel.mainScreenItemList.collectLatest { mainScreenItemList ->
                val adapter = MainScreenAdapter(mainScreenItemList, ::onClick)
                binding.recycleMainScreen.adapter = adapter
            }
        }
        /*      binding.apply {
                  fuelExpense.setOnClickListener {
                      findNavController().navigate(R.id.action_mainScreenFragment_to_fuelExpenseFragment)
                  }
                  fuelEfficiency.setOnClickListener {
                      findNavController().navigate(R.id.action_mainScreenFragment_to_fuelEfficiencyFragment)
                  }
                  History.setOnClickListener {
                      findNavController().navigate(R.id.action_mainScreenFragment_to_historyFragment)
                  }
              }
      */

        launchWhenStarted {
            mainScreenViewModel.mainScreenItemsClickEvent.collect { event ->
                when (event) {
                    is MainScreenEvent.Navigate -> findNavController().navigate(event.action)
                }
            }
        }
    }

    private fun onClick(mainScreenItem: MainScreenItem) {
        mainScreenViewModel.onMainScreenItemClick(mainScreenItem.action)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}