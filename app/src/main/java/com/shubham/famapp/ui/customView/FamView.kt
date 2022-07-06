package com.shubham.famapp.ui.customView

import android.content.Context
import android.util.AttributeSet
import android.util.Log
import android.view.View
import android.widget.LinearLayout
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.shubham.famapp.R
import com.shubham.famapp.databinding.FamViewBinding
import com.shubham.famapp.domain.model.FamCardModel

class FamView @JvmOverloads constructor(
    context: Context,
    var attrs: AttributeSet?,
    defStyleAttr: Int = 0,
    defStyleRes: Int = 0,
) : LinearLayout(
    context,
    attrs,
    defStyleAttr,
    defStyleRes
) {
    init {
        initView()
    }

    private fun initView() {
        inflate(context, R.layout.fam_view,this)
        val styleAttrs = context.theme.obtainStyledAttributes(
            attrs,
            R.styleable.FamView,
            0, 0
        )
        try {
            val stringListData = styleAttrs.getString(R.styleable.FamView_listData)

        } finally {
            styleAttrs.recycle()
        }
    }
    val live = MutableLiveData<FamCardModel>()
    private lateinit var binding: FamViewBinding
    lateinit var listData: FamCardModel
}