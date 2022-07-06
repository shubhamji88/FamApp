package com.shubham.famapp.data.entity

import com.google.gson.annotations.SerializedName
import com.shubham.famapp.domain.model.TextEntitiesModel

data class TextEntitiesEntity (
    @SerializedName("text")
    val text: String?,
    @SerializedName("color")
    val color: String?
    )
fun TextEntitiesEntity.convertToModel() = TextEntitiesModel(text, color)