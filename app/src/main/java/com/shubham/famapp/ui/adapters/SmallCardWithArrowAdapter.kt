package com.shubham.famapp.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.shubham.famapp.Utils
import com.shubham.famapp.databinding.ItemHc6Binding
import com.shubham.famapp.domain.model.CardModel


class SmallCardWithArrowAdapter(private val clickListener: FamClickListener,private val isScrollable:Boolean,private val numberOfItems:Int) : ListAdapter<CardModel, SmallCardWithArrowAdapter.ViewHolder>(CardRecyclerViewDiffCallBack()) {

    class ViewHolder private constructor(private val binding: ItemHc6Binding) : RecyclerView.ViewHolder(binding.root){

        fun bind(item: CardModel, clickListener: FamClickListener,) {
            val title = Utils.getFormattedText(item.formattedTitle) ?: item.title
            val description = Utils.getFormattedText(item.formattedDescription) ?: item.description
            if(item.url!=null && !item.isDisabled){
                binding.rootViewCv.setOnClickListener {
                    clickListener.openUrl(item.url)
                }
            }
            if(item.icon?.imageType!=null && item.icon.imageType == "ext") {
                binding.imageURL = item.icon.imageUrl
            }
            binding.title.text = title
            binding.description.text = description
            binding.title.gravity = Utils.getTextAlignment(item.formattedTitle)
            binding.rootViewCv.isEnabled = !item.isDisabled
            binding.executePendingBindings()
        }
        companion object {
            fun from(parent: ViewGroup, isScrollable: Boolean, numberOfItems: Int): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = ItemHc6Binding.inflate(layoutInflater, parent, false)
                val viewHolder = ViewHolder(binding)
                if(!isScrollable){
                    viewHolder.itemView.layoutParams.width = Utils.calculateViewWidth(parent.context,numberOfItems)
                }
                return viewHolder
            }
        }
    }
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item,clickListener)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent,isScrollable,numberOfItems)
    }
}