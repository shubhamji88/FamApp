package com.shubham.famapp.domain.model

data class CardGroupModel(
    val cardType: Int?,
    var cards: MutableList<CardModel?>?,
    val designType: String,
    val height: Int?,
    val id: Int,
    val isScrollable: Boolean =false,
    val level: Int?,
    val name: String?
)