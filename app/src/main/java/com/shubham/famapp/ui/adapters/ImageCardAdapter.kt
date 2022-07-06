package com.shubham.famapp.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.shubham.famapp.databinding.ItemHc1Binding
import com.shubham.famapp.databinding.ItemHc5Binding
import com.shubham.famapp.databinding.ItemRecyclerViewBinding
import com.shubham.famapp.domain.model.CardModel

class ImageCardAdapter : ListAdapter<CardModel, ImageCardAdapter.ViewHolder>(CardRecyclerViewDiffCallBack()) {

    class ViewHolder private constructor(private val binding: ItemHc5Binding) : RecyclerView.ViewHolder(binding.root){

        fun bind(item: CardModel) {
            binding.executePendingBindings()
        }
        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = ItemHc5Binding.inflate(layoutInflater, parent, false)
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