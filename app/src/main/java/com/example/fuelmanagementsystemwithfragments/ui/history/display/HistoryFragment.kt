package com.example.fuelmanagementsystemwithfragments.ui.history.display

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.fuelmanagementsystemwithfragments.R
import com.example.fuelmanagementsystemwithfragments.databinding.FragmentHistoryBinding

class HistoryFragment : Fragment(R.layout.fragment_history) {
    private var _binding: FragmentHistoryBinding? = null
    private val binding get() = _binding!!


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentHistoryBinding.bind(view)
        binding.fuelExpenseButton.setOnClickListener {
            findNavController().navigate(R.id.action_historyFragment_to_fuelExpenseHistoryFragment)
        }
        binding.fuelEfficiencyButton.setOnClickListener {
            findNavController().navigate(R.id.action_historyFragment_to_fuelEfficiencyHistoryFragment)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
