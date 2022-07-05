package com.shubham.famapp.domain.model

data class CardModel(
    val bgColor: String?,
    val bgImage: BgImageModel?,
    val cta: List<CtaModel?>?,
    val description: String?,
    val formattedDescription: FormattedDescriptionModel?,
    val formattedTitle: FormattedTitleModel?,
    val icon: IconModel?,
    val isDisabled: Boolean?,
    val name: String?,
    val title: String?,
    val url: String?
)