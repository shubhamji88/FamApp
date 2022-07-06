package com.shubham.famapp.ui.customView


import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.net.Uri
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import androidx.core.content.ContextCompat.startActivity
import androidx.databinding.DataBindingUtil
import com.shubham.famapp.R
import com.shubham.famapp.data.SharedPrefManager
import com.shubham.famapp.databinding.FamViewBinding
import com.shubham.famapp.domain.model.CardGroupModel
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
            dataReloaded()
            listData = data
            return
        }
        val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        binding = DataBindingUtil.inflate(inflater,R.layout.fam_view, this, true)
        listData = data
        initRecyclerView()
        removedDismissedData()
        handleSwipeReload(reloadClickListener)
    }

    override fun onAttachedToWindow() {
        super.onAttachedToWindow()
        SharedPrefManager.instance.sharedPreferences.registerOnSharedPreferenceChangeListener(sharedPreferencesChangeListener)
    }

    override fun invalidate() {
        super.invalidate()
        SharedPrefManager.instance.sharedPreferences.unregisterOnSharedPreferenceChangeListener(sharedPreferencesChangeListener)
    }
    private fun removedDismissedData(): MutableList<CardGroupModel> {
        val blockedList = SharedPrefManager.instance.blockedCards?.toList()
        val newList = listData.toMutableList()
        newList.forEach { cardModel->
            if(cardModel.designType=="HC3"){
                newList.remove(cardModel)
                val tempCardModelCards =cardModel.cards?.toMutableList()
                if(blockedList?.let { tempCardModelCards?.removeAll(it) } == true) {
                    cardModel.cards = tempCardModelCards
                }else if(tempCardModelCards?.remove(blockedList?.get(0))==true){
                    cardModel.cards = tempCardModelCards
                }
                if(tempCardModelCards?.size!=0){
                    newList.add(cardModel)
                }
            }
        }
        listData = newList
        return newList
    }
    private val sharedPreferencesChangeListener =SharedPreferences.OnSharedPreferenceChangeListener{ _, _ ->
        dataReloaded()
    }
    private fun dataReloaded() {
        famAdapter.submitDesignList(removedDismissedData())
        binding.swipeLayout.isRefreshing = false
    }

    private fun initRecyclerView(){
        famAdapter = FamAdapter (FamClickListener{ url->
            openUrl(url)
        })
        famAdapter.submitDesignList(removedDismissedData())
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