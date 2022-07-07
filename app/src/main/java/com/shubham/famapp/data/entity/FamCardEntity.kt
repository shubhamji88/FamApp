package com.shubham.famapp.data.entity


import com.google.gson.annotations.SerializedName
import com.shubham.famapp.domain.model.CardGroupModel
import com.shubham.famapp.domain.model.FamCardModel
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass


data class FamCardEntity(
    @SerializedName("card_groups")
    val cardGroups: List<CardGroupEntity?>?
)
fun FamCardEntity.convertToDomain() : FamCardModel {
    if(cardGroups.isNullOrEmpty()){
        return FamCardModel(null)
    }
    val mutableCardGroup =cardGroups.toMutableList()
    mutableCardGroup.removeAll(listOf(null))
    return FamCardModel(cardGroups.map { it?.convertToModel()!! })
}
