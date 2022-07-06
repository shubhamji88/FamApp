package com.shubham.famapp.domain.model

data class CardModel(
    val bgColor: String?,
    val bgImage: BgImageModel?,
    val cta: List<CtaModel?>?,
    val description: String?,
    val formattedDescription: FormattedTextModel?,
    val formattedTitle: FormattedTextModel?,
    val icon: IconModel?,
    val isDisabled: Boolean=false,
    val name: String?,
    val title: String?,
    val url: String?
)