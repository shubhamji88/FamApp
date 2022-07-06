package com.shubham.famapp.ui.adapters

import android.animation.ObjectAnimator
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.shubham.famapp.Utils
import com.shubham.famapp.data.SharedPrefManager
import com.shubham.famapp.databinding.ItemHc3Binding
import com.shubham.famapp.domain.model.CardModel
import com.shubham.famapp.domain.model.CtaModel

class BigDisplayCardAdapter(private val clickListener: FamClickListener) : ListAdapter<CardModel, BigDisplayCardAdapter.ViewHolder>(CardRecyclerViewDiffCallBack()) {

    class ViewHolder private constructor(private val binding: ItemHc3Binding) : RecyclerView.ViewHolder(binding.root){
        private fun updateCallToAction(item: CtaModel, clickListener: FamClickListener){
            if(item.url!=null) {
                binding.actionBtn.setOnClickListener {
                    clickListener.openUrl(item.url)
                }
            }
            binding.actionBtn.text = item.text
            binding.actionBtn.setTextColor(Color.parseColor(item.textColor))
            binding.actionBtn.setBackgroundColor(Color.parseColor(item.bgColor))
        }
        private fun handleAnimations(){
            var isSlided = false
            val openSlideAnimator = ObjectAnimator.ofFloat(binding.movingViewCl, View.TRANSLATION_X,600f)
            val closeAnimator = ObjectAnimator.ofFloat(binding.movingViewCl, View.TRANSLATION_X,0f)
            openSlideAnimator.duration = 600
            closeAnimator.duration = 600
            binding.movingViewCl.setOnLongClickListener {
                if(!isSlided) {
                    openSlideAnimator.start()
                    isSlided = true
                }else{
                    closeAnimator.start()
                    isSlided=false
                }
                true
            }
        }
        private fun handleSlidedButton(item: CardModel){
            binding.dismissCv.setOnClickListener {
                val blockedCards =SharedPrefManager.instance.blockedCards?.toMutableList()?: mutableListOf()
//                if (!blockedCards.contains(item))
                    blockedCards.add(item)
                SharedPrefManager.instance.blockedCards=blockedCards
            }
        }
        fun bind(item: CardModel, clickListener: FamClickListener) {
            handleAnimations()
            handleSlidedButton(item)
            val title = Utils.getFormattedText(item.formattedTitle) ?: item.title
            val description = Utils.getFormattedText(item.formattedDescription) ?: item.description
            if(item.url!=null && !item.isDisabled){
                binding.movingViewCl.setOnClickListener {
                    clickListener.openUrl(item.url)
                }
            }
            if(item.bgImage?.imageUrl!=null && item.bgImage.imageType == "ext") {
                binding.imageURL = item.bgImage.imageUrl
            }
            if(item.cta!=null && item.cta[0]!=null){
                updateCallToAction(item.cta[0]!!,clickListener)
            }
            binding.titleTv.text = title
            binding.descriptionTv.text= description
            binding.titleTv.gravity = Utils.getTextAlignment(item.formattedTitle)
            binding.rootViewCv.isEnabled = !item.isDisabled
            binding.executePendingBindings()
        }
        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = ItemHc3Binding.inflate(layoutInflater, parent, false)
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