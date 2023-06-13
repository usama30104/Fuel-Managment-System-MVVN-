package com.example.fuelmanagementsystemwithfragments.ui.history.display

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import com.example.fuelmanagementsystemwithfragments.R
import com.example.fuelmanagementsystemwithfragments.databinding.FragmentHistoryBinding
import com.example.fuelmanagementsystemwithfragments.ui.baseclasses.BaseFragment

//class A : B<FragmentHistoryBinding>() {
//
//
//
//}
//class D : B <FragmentHistoryBindingsdfasd> () {
//}
//open class  B<b : Binding>: Fragment() {
//    fun onCreateMY() {
//
//    }
//}
class HistoryFragment : BaseFragment<FragmentHistoryBinding>() {
    override fun getFragmentView(): Int = R.layout.fragment_history


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding?.apply {
            fuelExpenseButton.setOnClickListener {
                findNavController().navigate(R.id.action_historyFragment_to_fuelExpenseHistoryFragment)
            }
            fuelEfficiencyButton.setOnClickListener {
                findNavController().navigate(R.id.action_historyFragment_to_fuelEfficiencyHistoryFragment)
            }
        }
    }


    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}
