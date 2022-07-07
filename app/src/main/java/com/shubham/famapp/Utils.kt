package com.shubham.famapp

import android.content.Context
import android.content.res.Resources
import android.text.Html
import android.text.Spanned
import android.util.TypedValue
import android.view.Gravity
import android.widget.ImageView
import androidx.core.net.toUri
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.shubham.famapp.domain.model.BgImageModel
import com.shubham.famapp.domain.model.FormattedTextModel
import com.shubham.famapp.domain.model.TextEntitiesModel

object Utils {
    fun getFormattedText(data: FormattedTextModel?): Spanned? {
        if (data?.entities == null || data.text == null) {
            return null
        }
        val entities = data.entities
        var resultText = "<string>" + data.text
        entities.forEach { model ->
            resultText =
                resultText!!.replace("{}", "<font color='${model?.color}'>${model?.text}</font>")
        }
        resultText += "</string>"
        return Html.fromHtml(resultText)
    }

    fun getTextAlignment(data: FormattedTextModel?): Int {
        return when (data?.align) {
            "left" -> Gravity.LEFT
            "right" -> Gravity.RIGHT
            "center" -> Gravity.CENTER
            else -> Gravity.NO_GRAVITY
        }
    }

    val Number.toPx
        get() = TypedValue.applyDimension(
            TypedValue.COMPLEX_UNIT_DIP,
            this.toFloat(),
            Resources.getSystem().displayMetrics
        )

    fun calculateViewWidth(context: Context, itemCount: Int): Int {
        val displayMetrics = context.resources.displayMetrics
        val widthPixels = context.resources.displayMetrics.widthPixels
        val padding = (58 * displayMetrics.density).toInt()
        return (widthPixels - padding) / itemCount
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
                .error(R.drawable.transparent)
        )
        .into(imgView)
}