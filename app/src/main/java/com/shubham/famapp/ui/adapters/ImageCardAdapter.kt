package com.shubham.famapp.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.shubham.famapp.Utils
import com.shubham.famapp.databinding.ItemHc5Binding
import com.shubham.famapp.domain.model.CardModel

class ImageCardAdapter(private val clickListener: FamClickListener) : ListAdapter<CardModel, ImageCardAdapter.ViewHolder>(CardRecyclerViewDiffCallBack()) {

    class ViewHolder private constructor(private val binding: ItemHc5Binding) : RecyclerView.ViewHolder(binding.root){

        fun bind(item: CardModel, clickListener: FamClickListener) {
            if(item.url!=null && !item.isDisabled){
                binding.rootViewCv.setOnClickListener {
                    clickListener.openUrl(item.url)
                }
            }
            if(item.bgImage?.imageType==null && item.bgImage?.imageType == "ext") {
                binding.imageURL = item.bgImage.imageUrl
            }
            binding.rootViewCv.isEnabled = !item.isDisabled
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
        holder.bind(item,clickListener)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent)
    }
}