package com.shubham.famapp.data.entity


import com.google.gson.annotations.SerializedName
import com.shubham.famapp.domain.model.FormattedDescriptionModel
import com.shubham.famapp.domain.model.FormattedTitleModel
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

data class FormattedTitleEntity(
    @SerializedName("align")
    val align: String?,
    @SerializedName("entities")
    val entities: List<Any?>?,
    @SerializedName("text")
    val text: String?
)
fun FormattedTitleEntity.convertToModel(): FormattedTitleModel = FormattedTitleModel(align, entities, text)