package com.shubham.famapp.data.entity


import com.google.gson.annotations.SerializedName
import com.shubham.famapp.domain.model.IconModel

data class IconEntity(
    @SerializedName("aspect_ratio")
    val aspectRatio: Double?,
    @SerializedName("image_type")
    val imageType: String?,
    @SerializedName("image_url")
    val imageUrl: String?
)
fun IconEntity.convertToModel():IconModel = IconModel(aspectRatio, imageType, imageUrl)