package com.shubham.famapp.data.entity


import com.shubham.famapp.domain.model.CardGroupModel
import com.shubham.famapp.domain.model.CardModel
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class CardGroupEntity(
    @Json(name = "card_type")
    val cardType: Int?,
    @Json(name = "cards")
    val cards: List<CardEntity?>?,
    @Json(name = "design_type")
    val designType: String?,
    @Json(name = "height")
    val height: Int?,
    @Json(name = "id")
    val id: Int?,
    @Json(name = "is_scrollable")
    val isScrollable: Boolean?,
    @Json(name = "level")
    val level: Int?,
    @Json(name = "name")
    val name: String?
)
fun CardGroupEntity.convertToModel() : CardGroupModel = CardGroupModel(cardType,cards?.map { it?.convertToModel() },designType,height,id,isScrollable,level, name)