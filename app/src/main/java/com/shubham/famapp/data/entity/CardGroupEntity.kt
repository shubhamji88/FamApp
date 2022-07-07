package com.shubham.famapp.data.entity


import com.google.gson.annotations.SerializedName
import com.shubham.famapp.domain.model.CardGroupModel
import com.shubham.famapp.domain.model.CardModel


data class CardGroupEntity(
    @SerializedName("card_type")
    val cardType: Int?,
    @SerializedName("cards")
    val cards: List<CardEntity?>?,
    @SerializedName("design_type")
    val designType: String?,
    @SerializedName("height")
    val height: Int?,
    @SerializedName("id")
    val id: Int?,
    @SerializedName("is_scrollable")
    val isScrollable: Boolean = false,
    @SerializedName("level")
    val level: Int?,
    @SerializedName("name")
    val name: String?
)

fun CardGroupEntity.convertToModel(): CardGroupModel {
    return CardGroupModel(cardType, cards?.mapIndexed { index, cardEntity ->
        CardModel(
            (id ?: 0) + (level ?: 0) + index,
            cardEntity?.bgColor,
            cardEntity?.bgImage?.convertToModel(),
            cardEntity?.cta?.map { it?.convertToModel() },
            cardEntity?.description,
            cardEntity?.formattedDescription?.convertToModel(),
            cardEntity?.formattedTitle?.convertToModel(),
            cardEntity?.icon?.convertToModel(),
            cardEntity?.isDisabled ?: false,
            name,
            cardEntity?.title,
            cardEntity?.url
        )
    }?.toMutableList(), designType!!, height, id!!, isScrollable, level, name)
}

