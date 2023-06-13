package com.example.fuelmanagementsystemwithfragments.ui.history.fuelEfficiencyHistory.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.fuelmanagementsystemwithfragments.data.model.FuelEfficiencyCalculation
import com.example.fuelmanagementsystemwithfragments.databinding.FuelEfficiencyItemBinding

class FueLEfficiencyHistoryAdapter :
    ListAdapter<FuelEfficiencyCalculation, FueLEfficiencyHistoryAdapter.ViewHolder>(
        FuelEfficiencyDiffUtil()
    ) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            FuelEfficiencyItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val fuelEfficiencyCalculation = getItem(position)
        holder.bind(fuelEfficiencyCalculation)
    }

    class FuelEfficiencyDiffUtil : DiffUtil.ItemCallback<FuelEfficiencyCalculation>() {
        override fun areItemsTheSame(
            oldItem: FuelEfficiencyCalculation,
            newItem: FuelEfficiencyCalculation
        ): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(
            oldItem: FuelEfficiencyCalculation,
            newItem: FuelEfficiencyCalculation
        ): Boolean {
            return oldItem == newItem
        }

    }

    inner class ViewHolder(private val binding: FuelEfficiencyItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(fuelEfficiencyCalculation: FuelEfficiencyCalculation) {

            binding.apply {
                initialFuelResult.text = fuelEfficiencyCalculation.initialFuel.toString()
                initalReadingResult.text = fuelEfficiencyCalculation.initialReading.toString()
                fuelPriceAtStartResult.text = fuelEfficiencyCalculation.petrolPriceAtRefil.toString()
                usedFuelResult.text = fuelEfficiencyCalculation.fuelUsed.toString()
                finalReadingResult.text = fuelEfficiencyCalculation.finalReading.toString()
                finalPriceAtEndResult.text = fuelEfficiencyCalculation.fuelPriceAtEnd.toString()
                fuelDifferenceResult.text = fuelEfficiencyCalculation.fuelDifference.toString()
                calculateFuelEfficiencyResult.text = fuelEfficiencyCalculation.result
            }
        }
    }
}