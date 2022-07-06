package com.shubham.famapp

import android.view.Gravity
import android.widget.ImageView
import androidx.core.net.toUri
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.shubham.famapp.domain.model.BgImageModel
import com.shubham.famapp.domain.model.FormattedTextModel
import com.shubham.famapp.domain.model.TextEntitiesModel

class Utils {
    companion object{
        fun getFormattedText(data : FormattedTextModel?): String? {
            if(data?.entities == null || data.text==null){
                return null
            }
            val entities = data.entities as  List<TextEntitiesModel>
            val resultText = data.text
            entities.forEach { model ->
                resultText.replace("{}","<font color='${model.color}'>${model.text}</font>")
            }
            return resultText
        }
        fun getTextAlignment(data : FormattedTextModel?): Int {
            return when(data?.align){
                "left" -> Gravity.LEFT
                "right" -> Gravity.RIGHT
                "center" -> Gravity.CENTER
                else -> Gravity.NO_GRAVITY
            }
        }

        fun loadImage(imgView: ImageView, url: String?, size: Double?) {
            val imgUri = url?.toUri()?.buildUpon()?.scheme("https")?.build()
            Glide.with(imgView.context)
                .load(imgUri)
                .apply(
                    RequestOptions()
                        .placeholder(R.drawable.ic_baseline_loading_24)
                        .error(R.drawable.asset_accout)
                )
                .into(imgView)
        }
    }
}

@BindingAdapter("imageUrl")
fun bindImage(imgView: ImageView, imgUrl: String?) {
        val imgUri = imgUrl?.toUri()?.buildUpon()?.scheme("https")?.build()
        Glide.with(imgView.context)
            .load(imgUri)
            .apply(
                RequestOptions()
                    .placeholder(R.drawable.ic_baseline_loading_24)
                    .error(R.drawable.asset_accout)
            )
            .into(imgView)
}