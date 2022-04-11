package com.univer.carshowroom

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.univer.carshowroom.databinding.ItemRecyclerBinding

class CarsShowroomAdapter(
    private val clickRoot: (String, Boolean) -> Unit
) : ListAdapter<AdapterEntity, CarsShowroomAdapter.CarsViewHolder>(CarsShowroomDiffUtil()) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CarsViewHolder {
        val binding =
            ItemRecyclerBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CarsViewHolder(
            binding
        )
    }

    override fun onBindViewHolder(holder: CarsViewHolder, position: Int) {
        holder.bindVoiceRec(getItem(position))
    }

    inner class CarsViewHolder(
        private val binding: ItemRecyclerBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bindVoiceRec(adapterEntity: AdapterEntity) {
            binding.isClicked = adapterEntity.isClicked
            binding.text = adapterEntity.text

//            Log.d("test12", adapterEntity.toString())

            binding.root.setOnClickListener {
//                val newList = mutableListOf<AdapterEntity>()

//                currentList.forEach {
//                    if (it != adapterEntity)
//                        newList.add(AdapterEntity(it.text, false))
//                    else
//                        newList.add(AdapterEntity(it.text, !it.isClicked))
//                }

                binding.isClicked = true
                clickRoot(adapterEntity.text, !adapterEntity.isClicked)
//                submitList(newList)
            }
//            binding.executePendingBindings()
        }
    }


    class CarsShowroomDiffUtil : DiffUtil.ItemCallback<AdapterEntity>() {
        override fun areItemsTheSame(
            oldItemVoiceRec: AdapterEntity,
            newItemVoiceRec: AdapterEntity
        ): Boolean {
            return oldItemVoiceRec.text == newItemVoiceRec.text
        }

        override fun areContentsTheSame(
            oldIteVoiceRec: AdapterEntity,
            newItemVoiceRec: AdapterEntity
        ): Boolean {
            return oldIteVoiceRec == newItemVoiceRec
        }
    }
}