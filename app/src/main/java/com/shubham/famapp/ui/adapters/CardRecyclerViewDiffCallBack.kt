package com.shubham.famapp.ui.adapters

import androidx.recyclerview.widget.DiffUtil
import com.shubham.famapp.domain.model.CardModel

class CardRecyclerViewDiffCallBack: DiffUtil.ItemCallback<CardModel>()
{
    override fun areItemsTheSame(oldItem: CardModel, newItem: CardModel): Boolean {
        return oldItem == newItem
    }
    override fun areContentsTheSame(oldItem: CardModel, newItem: CardModel): Boolean {
        return oldItem==newItem
    }
}