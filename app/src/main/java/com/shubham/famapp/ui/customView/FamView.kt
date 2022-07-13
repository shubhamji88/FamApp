package com.shubham.famapp.ui.customView


import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.net.Uri
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.LinearLayout
import androidx.core.content.ContextCompat.startActivity
import androidx.databinding.DataBindingUtil
import com.shubham.famapp.R
import com.shubham.famapp.data.SharedPrefManager
import com.shubham.famapp.data.SharedPrefManager.Companion.BLOCKED_CARD_LIST
import com.shubham.famapp.data.SharedPrefManager.Companion.SNOOZED_CARD_LIST
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
){

    private lateinit var famAdapter : FamAdapter
    private lateinit var binding: FamViewBinding
    private lateinit var listData: List<CardGroupModel>


    init{
        initView()
    }
    private fun initView(){
        val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        binding = DataBindingUtil.inflate(inflater,R.layout.fam_view, this, true)
    }
    /**
     * @param data: List of CardGroupModel that is required to display data
     * @param reloadClickListener: used when user use swipe gesture to reload data
     */
    fun initDataAndReloadClickListener(data: List<CardGroupModel>, reloadClickListener:ReloadClickListener) {
        // For subsequent call of this function, we only need to update the data
        if(binding.progressPb.visibility==View.VISIBLE){
            initRecyclerView()
            removeProgressBar()
        }
        listData = data
        dataReloaded(removedFromData(BLOCKED_CARD_LIST))
        handleSwipeReload(reloadClickListener)
    }

    private fun removeProgressBar() {
        binding.progressPb.visibility = View.GONE
    }
    override fun onAttachedToWindow(){
        super.onAttachedToWindow()
        SharedPrefManager.instance.sharedPreferences.registerOnSharedPreferenceChangeListener(sharedPreferencesChangeListener)
    }
    override fun onDetachedFromWindow() {
        super.onDetachedFromWindow()
        SharedPrefManager.instance.sharedPreferences.unregisterOnSharedPreferenceChangeListener(sharedPreferencesChangeListener)
    }

    /**
     * Used when user "press dismiss" or "remind later" in HC3.
     * According to key, the dismissed/snoozed list if fetched from shared Preferences and dataReload is called with new
     * list with removed dismissed/snoozed elements
     */
    private val sharedPreferencesChangeListener =SharedPreferences.OnSharedPreferenceChangeListener{ _, key ->
            dataReloaded(removedFromData(key))
        }

    /**
     * @param data: New data to be provided to recycler view
     */
    private fun dataReloaded(data : MutableList<CardGroupModel>) {
        famAdapter.submitDesignList(data)
        binding.swipeLayout.isRefreshing = false
    }

    /**
     * Initializes the main recycler view which has recycler views as items in it
     */
    private fun initRecyclerView(){
        famAdapter = FamAdapter (FamClickListener{ url->
            openUrl(url)
        })
        binding.mainRv.adapter = famAdapter
    }

    /**
     * This function calls the ReloadClickListener which should be implemented by the fragment/activity
     * @param reloadClickListener : to be called when swipe gesture is activated
     */
    private fun handleSwipeReload(reloadClickListener: ReloadClickListener) {
        binding.swipeLayout.setOnRefreshListener {
            reloadClickListener.onReload()
        }
    }

    /**
     * Used to open url if user clicks on card or CTA action button
     * @param url: url of the website
     */
    private fun openUrl(url:String){
        startActivity(context,
            Intent(
                Intent.ACTION_VIEW,
                Uri.parse(url)
            ),null
        )
    }

    /**
     * This function
     * @param sharedPrefKey : It can be BLOCKED_CARD_LIST or SNOOZED_CARD_LIST
     * @return MutableList<CardGroupModel>: removes the snoozed list or the dismissed list (depending upon key) from the listData and returns
     * mutable list
     */
    private fun removedFromData(sharedPrefKey:String): MutableList<CardGroupModel> {
        val blockedList = when(sharedPrefKey){
            BLOCKED_CARD_LIST -> SharedPrefManager.instance.blockedCards?.toList()
            SNOOZED_CARD_LIST -> SharedPrefManager.instance.snoozedCards?.toList()
            else -> emptyList()
        }
        if(blockedList.isNullOrEmpty()){
            return listData.toMutableList()
        }
        val newList = listData.toMutableList()
        newList.forEach { cardModel->
            if(cardModel.designType=="HC3"){
                cardModel.cards?.forEach { card->
                    if(card!=null && blockedList.contains(card.uid)){
                        newList.remove(cardModel)
                    }
                }
            }
        }
        return newList
    }

}
class ReloadClickListener(val reloadClickListener: (Unit)-> Unit){
    fun onReload()= reloadClickListener(Unit)
}