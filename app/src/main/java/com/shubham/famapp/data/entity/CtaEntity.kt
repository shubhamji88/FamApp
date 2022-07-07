package com.shubham.famapp.data.entity


import com.google.gson.annotations.SerializedName
import com.shubham.famapp.domain.model.CtaModel


data class CtaEntity(
    @SerializedName("bg_color")
    val bgColor: String?,
    @SerializedName("text")
    val text: String?,
    @SerializedName("text_color")
    val textColor: String?,
    @SerializedName("url")
    val url: String?,
    @SerializedName("url_choice")
    val urlChoice: String?
)
fun CtaEntity.convertToModel() : CtaModel = CtaModel(bgColor,text,textColor,url,urlChoice)