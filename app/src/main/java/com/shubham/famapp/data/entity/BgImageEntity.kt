package com.shubham.famapp.data.entity


import com.google.gson.annotations.SerializedName
import com.shubham.famapp.domain.model.BgImageModel
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass


data class BgImageEntity(
    @SerializedName("aspect_ratio")
    val aspectRatio: Double?,
    @SerializedName("image_type")
    val imageType: String?,
    @SerializedName("image_url")
    val imageUrl: String?
)

fun BgImageEntity.convertToModel() : BgImageModel{
    return BgImageModel(aspectRatio,imageType,imageUrl)
}