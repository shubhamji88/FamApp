package com.shubham.famapp.data.entity


import com.shubham.famapp.domain.model.IconModel
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class IconEntity(
    @Json(name = "aspect_ratio")
    val aspectRatio: Double?,
    @Json(name = "image_type")
    val imageType: String?,
    @Json(name = "image_url")
    val imageUrl: String?
)
fun IconEntity.convertToModel():IconModel = IconModel(aspectRatio, imageType, imageUrl)