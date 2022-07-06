package com.shubham.famapp.domain.model

data class CardGroupModel(
    val cardType: Int?,
    val cards: List<CardModel?>?,
    val designType: String,
    val height: Int?,
    val id: Int,
    val isScrollable: Boolean?,
    val level: Int?,
    val name: String?
)