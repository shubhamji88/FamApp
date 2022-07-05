package com.shubham.famapp.data.entity


import com.shubham.famapp.domain.model.CtaModel
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class CtaEntity(
    @Json(name = "bg_color")
    val bgColor: String?,
    @Json(name = "text")
    val text: String?,
    @Json(name = "text_color")
    val textColor: String?,
    @Json(name = "url")
    val url: String?,
    @Json(name = "url_choice")
    val urlChoice: String?
)
fun CtaEntity.convertToModel() : CtaModel? = CtaModel(bgColor,text,textColor,url,urlChoice)