package com.shubham.famapp.data.entity


import com.google.gson.annotations.SerializedName


data class CardEntity(
    @SerializedName("bg_color")
    val bgColor: String?,
    @SerializedName("bg_image")
    val bgImage: BgImageEntity?,
    @SerializedName("cta")
    val cta: List<CtaEntity?>?,
    @SerializedName("description")
    val description: String?,
    @SerializedName("formatted_description")
    val formattedDescription: FormattedDescriptionEntity?,
    @SerializedName("formatted_title")
    val formattedTitle: FormattedTitleEntity?,
    @SerializedName("icon")
    val icon: IconEntity?,
    @SerializedName("is_disabled")
    val isDisabled: Boolean=false,
    @SerializedName("name")
    val name: String?,
    @SerializedName("title")
    val title: String?,
    @SerializedName("url")
    val url: String?

)
