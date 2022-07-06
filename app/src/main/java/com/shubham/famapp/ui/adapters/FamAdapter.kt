package com.shubham.famapp.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.shubham.famapp.databinding.ItemRecyclerViewBinding
import com.shubham.famapp.domain.DesignTypes
import com.shubham.famapp.domain.model.CardGroupModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class FamAdapter : ListAdapter<DesignTypes, FamAdapter.ViewHolder>(FamRecyclerViewDiffCallBack()) {
    private val adapterScope = CoroutineScope(Dispatchers.Default)

    fun addHeaderAndSubmitList(list: List<CardGroupModel>) {
        adapterScope.launch {
            val items = list.map { DesignTypes.getType(it.designType,it) }
            withContext(Dispatchers.Main) {
                submitList(items)
            }
        }
    }
    class FamRecyclerViewDiffCallBack : DiffUtil.ItemCallback<DesignTypes>()
    {
        override fun areItemsTheSame(oldItem: DesignTypes, newItem: DesignTypes): Boolean {
            return oldItem.id == newItem.id
        }
        override fun areContentsTheSame(oldItem: DesignTypes, newItem: DesignTypes): Boolean {
            return oldItem==newItem
        }
    }
    class ViewHolder private constructor(val binding: ItemRecyclerViewBinding) : RecyclerView.ViewHolder(binding.root){

        fun bind(item: DesignTypes) {
//            when(item){
//                is DesignTypes.SMALL_DISPLAY_CLASS -> item.cardData
//            }
            binding.executePendingBindings()
        }

        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = ItemRecyclerViewBinding.inflate(layoutInflater, parent, false)
                return ViewHolder(binding)
            }
        }
    }
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)
//        holder.bind(item,clickListner)
        holder.bind(item)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent)
    }
}