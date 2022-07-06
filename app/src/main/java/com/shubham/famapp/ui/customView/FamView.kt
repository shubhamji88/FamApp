package com.shubham.famapp.ui.customView


import android.content.Context
import android.content.Intent
import android.net.Uri
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import androidx.core.content.ContextCompat.startActivity
import androidx.databinding.DataBindingUtil
import com.shubham.famapp.R
import com.shubham.famapp.databinding.FamViewBinding
import com.shubham.famapp.domain.model.FamCardModel
import com.shubham.famapp.ui.adapters.FamAdapter
import com.shubham.famapp.ui.adapters.FamClickListener


class FamView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet?,
    defStyleAttr: Int = 0,
    defStyleRes: Int = 0,
) : LinearLayout(
    context,
    attrs,
    defStyleAttr,
    defStyleRes
) {


    fun initView(data: FamCardModel) {
        listData = data
        val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        binding = DataBindingUtil.inflate(inflater,R.layout.fam_view, this, true)
        val famAdapter = FamAdapter (FamClickListener{ url->
            openUrl(url)
        })
        famAdapter.submitDesignList(data.cardGroups!!)
        binding.mainRv.adapter = famAdapter
    }
    private fun openUrl(url:String){
        startActivity(context,
            Intent(
                Intent.ACTION_VIEW,
                Uri.parse(url)
            ),null
        )
    }

    private lateinit var binding: FamViewBinding
    lateinit var listData: FamCardModel
}