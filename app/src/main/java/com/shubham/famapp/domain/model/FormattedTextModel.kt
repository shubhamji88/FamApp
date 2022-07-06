package com.shubham.famapp.domain.model

data class FormattedTextModel(
    val align: String?,
    val entities: List<TextEntitiesModel?>?,
    val text: String?
)