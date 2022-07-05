package com.shubham.famapp.data.entity


import com.shubham.famapp.domain.model.FormattedDescriptionModel
import com.shubham.famapp.domain.model.FormattedTitleModel
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class FormattedTitleEntity(
    @Json(name = "align")
    val align: String?,
    @Json(name = "entities")
    val entities: List<Any?>?,
    @Json(name = "text")
    val text: String?
)
fun FormattedTitleEntity.convertToModel(): FormattedTitleModel = FormattedTitleModel(align, entities, text)