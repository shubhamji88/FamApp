package com.shubham.famapp.data.entity


import com.shubham.famapp.domain.model.CardGroupModel
import com.shubham.famapp.domain.model.FamCardModel
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class FamCardEntity(
    @Json(name = "card_groups")
    val cardGroups: List<CardGroupEntity?>?
)

fun FamCardEntity.convertToDomain() : FamCardModel = FamCardModel(cardGroups?.map { it?.convertToModel() })
