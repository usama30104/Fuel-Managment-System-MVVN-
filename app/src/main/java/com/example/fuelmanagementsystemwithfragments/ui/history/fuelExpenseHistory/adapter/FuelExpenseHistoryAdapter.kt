package com.example.fuelmanagementsystemwithfragments.ui.history.fuelExpenseHistory.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.fuelmanagementsystemwithfragments.data.model.FuelExpenseCalculation
import com.example.fuelmanagementsystemwithfragments.databinding.FuelExpenseItemBinding

class FuelExpenseHistoryAdapter :
    ListAdapter<FuelExpenseCalculation, FuelExpenseHistoryAdapter.ViewHolder>(DiffUtil()) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            FuelExpenseItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val fuelExpenseCalculation = getItem(position)
        holder.bind(fuelExpenseCalculation)
    }

    class DiffUtil : androidx.recyclerview.widget.DiffUtil.ItemCallback<FuelExpenseCalculation>() {
        override fun areItemsTheSame(
            oldItem: FuelExpenseCalculation,
            newItem: FuelExpenseCalculation
        ): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(
            oldItem: FuelExpenseCalculation,
            newItem: FuelExpenseCalculation
        ): Boolean {
            return oldItem == newItem
        }

    }

    inner class ViewHolder(private val binding: FuelExpenseItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(fuelExpenseCalculation: FuelExpenseCalculation) {

            binding.apply {
                fuelPriceResult.text = fuelExpenseCalculation.fuelPrice.toString()
                averagePerKmResult.text = fuelExpenseCalculation.VehicleAverage.toString()
                totalDistanceTravelResult.text = fuelExpenseCalculation.totalDistance.toString()
                totalCostOfFuelResult.text = fuelExpenseCalculation.totalCalculation.toString()

            }
        }
    }

}