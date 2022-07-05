package com.shubham.famapp.data.entity


import com.shubham.famapp.domain.model.CardModel
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class CardEntity(
    @Json(name = "bg_color")
    val bgColor: String?,
    @Json(name = "bg_image")
    val bgImage: BgImageEntity?,
    @Json(name = "cta")
    val cta: List<CtaEntity?>?,
    @Json(name = "description")
    val description: String?,
    @Json(name = "formatted_description")
    val formattedDescription: FormattedDescriptionEntity?,
    @Json(name = "formatted_title")
    val formattedTitle: FormattedTitleEntity?,
    @Json(name = "icon")
    val icon: IconEntity?,
    @Json(name = "is_disabled")
    val isDisabled: Boolean?,
    @Json(name = "name")
    val name: String?,
    @Json(name = "title")
    val title: String?,
    @Json(name = "url")
    val url: String?
)
fun CardEntity.convertToModel() : CardModel {
    return CardModel(
        bgColor,bgImage?.convertToModel(),cta?.map { it?.convertToModel() },description,formattedDescription?.convertToModel(),formattedTitle?.convertToModel(),icon?.convertToModel(),isDisabled,name,title,url
    )
}