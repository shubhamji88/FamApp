package com.shubham.famapp.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.shubham.famapp.databinding.ItemHc1Binding
import com.shubham.famapp.databinding.ItemRecyclerViewBinding
import com.shubham.famapp.domain.model.CardModel

class SmallDisplayCardAdapter : ListAdapter<CardModel, SmallDisplayCardAdapter.ViewHolder>(CardRecyclerViewDiffCallBack()) {

    class ViewHolder private constructor(private val binding: ItemHc1Binding) : RecyclerView.ViewHolder(binding.root){

        fun bind(item: CardModel) {
            binding.executePendingBindings()
        }
        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = ItemHc1Binding.inflate(layoutInflater, parent, false)
                return ViewHolder(binding)
            }
        }
    }
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent)
    }
}