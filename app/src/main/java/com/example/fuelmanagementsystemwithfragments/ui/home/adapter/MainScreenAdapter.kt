package com.example.fuelmanagementsystemwithfragments.ui.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.fuelmanagementsystemwithfragments.databinding.MainScreenItemBinding
import com.example.fuelmanagementsystemwithfragments.ui.home.model.MainScreenItem

class MainScreenAdapter(
    private val list: List<MainScreenItem>,
    private val onClick: (mainScreenItem: MainScreenItem) -> Unit
) :
    RecyclerView.Adapter<MainScreenAdapter.ViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        return ViewHolder(
            MainScreenItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(list[position])
    }

    override fun getItemCount(): Int {
        return list.size
    }

    inner class ViewHolder(private val binding: MainScreenItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(mainScreenItem: MainScreenItem) {

            binding.apply {
                title.text = root.context.getString(mainScreenItem.nameResId)
                mainScreenImage.setImageResource(mainScreenItem.imgResId)

                root.setOnClickListener {
                    onClick(mainScreenItem)
                }
            }
        }
    }
}