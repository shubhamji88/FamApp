package com.shubham.famapp.data.entity


import com.google.gson.annotations.SerializedName
import com.shubham.famapp.domain.model.CardGroupModel
import com.shubham.famapp.domain.model.CardModel
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass


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
    val isScrollable: Boolean?,
    @SerializedName("level")
    val level: Int?,
    @SerializedName("name")
    val name: String?
)
//TODO remove !!
fun CardGroupEntity.convertToModel() : CardGroupModel = CardGroupModel(cardType,cards?.map { it?.convertToModel() },designType!!,height,id!!,isScrollable,level, name)