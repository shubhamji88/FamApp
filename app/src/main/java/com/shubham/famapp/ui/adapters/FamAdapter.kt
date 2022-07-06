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

    fun submitDesignList(list: List<CardGroupModel>) {
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
    class ViewHolder private constructor(private val binding: ItemRecyclerViewBinding) : RecyclerView.ViewHolder(binding.root){
        private fun bindSmallDisplayCard(cardData: CardGroupModel){
            val smallDisplayCardAdapter = SmallDisplayCardAdapter()
            binding.groupItemRv.adapter = smallDisplayCardAdapter
            smallDisplayCardAdapter.submitList(cardData.cards)
        }
        private fun bindBigDisplayCard(cardData: CardGroupModel){
            val bigDisplayCardAdapter = BigDisplayCardAdapter()
            binding.groupItemRv.adapter = bigDisplayCardAdapter
            bigDisplayCardAdapter.submitList(cardData.cards)
        }
        private fun bindImageCard(cardData: CardGroupModel){
            val imageCardAdapter = ImageCardAdapter()
            binding.groupItemRv.adapter = imageCardAdapter
            imageCardAdapter.submitList(cardData.cards)
        }
        private fun bindSmallCardWithArrowCard(cardData: CardGroupModel){
            val smallCardWithArrowAdapter = SmallCardWithArrowAdapter()
            binding.groupItemRv.adapter = smallCardWithArrowAdapter
            smallCardWithArrowAdapter.submitList(cardData.cards)
        }
        private fun bindDynamicWidthCard(cardData: CardGroupModel){
            val dynamicWidthCardAdapter = DynamicWidthCardAdapter()
            binding.groupItemRv.adapter = dynamicWidthCardAdapter
            dynamicWidthCardAdapter.submitList(cardData.cards)
        }

        fun bind(item: DesignTypes) {
            when(item){
                is DesignTypes.SMALL_DISPLAY_CLASS -> bindSmallDisplayCard(item.cardData)
                is DesignTypes.BIG_DISPLAY_CARD -> bindBigDisplayCard(item.cardData)
                is DesignTypes.IMAGE_CARD -> bindImageCard(item.cardData)
                is DesignTypes.SMALL_CARD_WITH_ARROW -> bindSmallCardWithArrowCard(item.cardData)
                is DesignTypes.DYNAMIC_WIDTH_CARD -> bindDynamicWidthCard(item.cardData)
            }
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
        holder.bind(item)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent)
    }
}