package com.shubham.famapp.data.entity


import com.google.gson.annotations.SerializedName
import com.shubham.famapp.domain.model.FormattedTextModel

data class FormattedTitleEntity(
    @SerializedName("align")
    val align: String?,
    @SerializedName("entities")
    val entities: List<TextEntitiesEntity?>?,
    @SerializedName("text")
    val text: String?
)
fun FormattedTitleEntity.convertToModel(): FormattedTextModel = FormattedTextModel(align, entities?.map { it?.convertToModel() }, text)