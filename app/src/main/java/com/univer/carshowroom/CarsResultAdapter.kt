package com.univer.carshowroom

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.univer.carshowroom.databinding.ItemCarsBinding

class CarsResultAdapter(
) : ListAdapter<Car, CarsResultAdapter.CarsResultViewHolder>(CarsResultDiffUtil()) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CarsResultViewHolder {
        val binding = ItemCarsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CarsResultViewHolder(
            binding
        )
    }

    override fun onBindViewHolder(holder: CarsResultViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class CarsResultViewHolder(
        private val binding: ItemCarsBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(car: Car) {
            binding.car = car
        }
    }


    class CarsResultDiffUtil : DiffUtil.ItemCallback<Car>() {
        override fun areItemsTheSame(
            oldItemVoiceRec: Car,
            newItemVoiceRec: Car
        ): Boolean {
            return oldItemVoiceRec.brand == newItemVoiceRec.brand
        }

        override fun areContentsTheSame(
            oldIteVoiceRec: Car,
            newItemVoiceRec: Car
        ): Boolean {
            return oldIteVoiceRec == newItemVoiceRec
        }
    }
}