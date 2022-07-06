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
import com.shubham.famapp.domain.model.CardGroupModel
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


    fun initView(data: List<CardGroupModel>, reloadClickListener:ReloadClickListener) {
        if(::binding.isInitialized){
            dataReloaded(data)
            return
        }
        val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        binding = DataBindingUtil.inflate(inflater,R.layout.fam_view, this, true)
        listData = data
        initRecyclerView()
        handleSwipeReload(reloadClickListener)
    }

    private fun dataReloaded(data: List<CardGroupModel>) {
        listData = data
        famAdapter.submitDesignList(listData)
        binding.swipeLayout.isRefreshing = false
    }

    private fun initRecyclerView(){
        famAdapter = FamAdapter (FamClickListener{ url->
            openUrl(url)
        })
        famAdapter.submitDesignList(listData)
        binding.mainRv.adapter = famAdapter
    }
    private fun handleSwipeReload(reloadClickListener: ReloadClickListener) {
        binding.swipeLayout.setOnRefreshListener {
            reloadClickListener.onReload()
        }
    }

    private fun openUrl(url:String){
        startActivity(context,
            Intent(
                Intent.ACTION_VIEW,
                Uri.parse(url)
            ),null
        )
    }
    private lateinit var famAdapter : FamAdapter
    private lateinit var binding: FamViewBinding
    private lateinit var listData: List<CardGroupModel>
}
class ReloadClickListener(val reloadClickListener: (Unit)-> Unit){
    fun onReload()= reloadClickListener(Unit)
}