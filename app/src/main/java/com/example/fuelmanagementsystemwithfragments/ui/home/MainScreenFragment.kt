package com.example.fuelmanagementsystemwithfragments.ui.home

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.fuelmanagementsystemwithfragments.R
import com.example.fuelmanagementsystemwithfragments.databinding.FragmentMainScreenBinding
import com.example.fuelmanagementsystemwithfragments.ui.baseclasses.BaseFragment
import com.example.fuelmanagementsystemwithfragments.ui.home.adapter.MainScreenAdapter
import com.example.fuelmanagementsystemwithfragments.ui.home.model.MainScreenItem
import com.example.fuelmanagementsystemwithfragments.utils.extension.launchWhenStarted
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest

@AndroidEntryPoint
class MainScreenFragment : BaseFragment<FragmentMainScreenBinding>() {
    private val mainScreenViewModel by viewModels<MainScreenViewModel>()
    override fun getFragmentView(): Int = R.layout.fragment_main_screen

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        launchWhenStarted {
            mainScreenViewModel.mainScreenItemList.collectLatest { mainScreenItemList ->
                val adapter = MainScreenAdapter(mainScreenItemList, ::onClick)
                binding!!.recycleMainScreen.adapter = adapter
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
                    is MainScreenViewModel.MainScreenEvent.Navigate -> {
                        findNavController().navigate(event.action)
                    }
                }
            }
        }
    }

    private fun onClick(mainScreenItem: MainScreenItem) {
        mainScreenViewModel.onMainScreenItemClick(mainScreenItem.action)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}