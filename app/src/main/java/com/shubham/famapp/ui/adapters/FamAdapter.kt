package com.shubham.famapp.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.shubham.famapp.Utils.toPx
import com.shubham.famapp.databinding.ItemRecyclerViewBinding
import com.shubham.famapp.domain.DesignTypes
import com.shubham.famapp.domain.model.CardGroupModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class FamAdapter(private val clickListener: FamClickListener) : ListAdapter<DesignTypes, FamAdapter.ViewHolder>(FamRecyclerViewDiffCallBack()) {
    private val adapterScope = CoroutineScope(Dispatchers.Default)

    /**
     * Here the list of modified according to its design type
     * @param list: this list supplied from FamView
     */
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
        private fun bindSmallDisplayCard(cardData: CardGroupModel, clickListener: FamClickListener){
            val smallDisplayCardAdapter = SmallDisplayCardAdapter(clickListener,cardData.isScrollable,cardData.cards?.size ?:1)
            binding.groupItemRv.adapter = smallDisplayCardAdapter
            smallDisplayCardAdapter.submitList(cardData.cards)
        }
        private fun bindBigDisplayCard(cardData: CardGroupModel, clickListener: FamClickListener){
            val bigDisplayCardAdapter = BigDisplayCardAdapter(clickListener,cardData.isScrollable,cardData.cards?.size ?:1)
            binding.groupItemRv.adapter = bigDisplayCardAdapter
            bigDisplayCardAdapter.submitList(cardData.cards)
        }
        private fun bindImageCard(cardData: CardGroupModel, clickListener: FamClickListener){
            val imageCardAdapter = ImageCardAdapter(clickListener,cardData.isScrollable,cardData.cards?.size ?:1)
            binding.groupItemRv.adapter = imageCardAdapter
            imageCardAdapter.submitList(cardData.cards)
        }
        private fun bindSmallCardWithArrowCard(cardData: CardGroupModel, clickListener: FamClickListener){
            val smallCardWithArrowAdapter = SmallCardWithArrowAdapter(clickListener,cardData.isScrollable,cardData.cards?.size ?:1)
            binding.groupItemRv.adapter = smallCardWithArrowAdapter
            smallCardWithArrowAdapter.submitList(cardData.cards)
        }
        private fun bindDynamicWidthCard(cardData: CardGroupModel, clickListener: FamClickListener){
            val dynamicWidthCardAdapter = DynamicWidthCardAdapter(clickListener)
            binding.groupItemRv.adapter = dynamicWidthCardAdapter
            if(cardData.height!=null){
                binding.groupItemRv.layoutParams.height=cardData.height.toPx.toInt()
            }
            dynamicWidthCardAdapter.submitList(cardData.cards)
        }

        /**
         * Used to bind different recycler view's as items for this recycler view depending upon their type
         * @param item: These are of DesignTypes which contains card data
         * @param clickListener: click listener to pass to famView to open URL
         */
        fun bind(item: DesignTypes,clickListener: FamClickListener) {
            when(item){
                is DesignTypes.SMALL_DISPLAY_CLASS -> bindSmallDisplayCard(item.cardData,clickListener)
                is DesignTypes.BIG_DISPLAY_CARD -> bindBigDisplayCard(item.cardData,clickListener)
                is DesignTypes.IMAGE_CARD -> bindImageCard(item.cardData,clickListener)
                is DesignTypes.SMALL_CARD_WITH_ARROW -> bindSmallCardWithArrowCard(item.cardData,clickListener)
                is DesignTypes.DYNAMIC_WIDTH_CARD -> bindDynamicWidthCard(item.cardData,clickListener)
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
        holder.bind(item,clickListener)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent)
    }
}
class FamClickListener(val openUrlClickListener: (String)-> Unit){
    fun openUrl(url :String)= openUrlClickListener(url)
}