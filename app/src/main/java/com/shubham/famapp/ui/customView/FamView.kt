package com.shubham.famapp.ui.customView


import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.net.Uri
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.LinearLayout
import android.widget.Toast
import androidx.core.content.ContextCompat.startActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.viewModelScope
import com.shubham.famapp.R
import com.shubham.famapp.data.SharedPrefManager
import com.shubham.famapp.data.SharedPrefManager.Companion.BLOCKED_CARD_LIST
import com.shubham.famapp.data.SharedPrefManager.Companion.SNOOZED_CARD_LIST
import com.shubham.famapp.databinding.FamViewBinding
import com.shubham.famapp.domain.model.CardGroupModel
import com.shubham.famapp.domain.usecase.GetCardDataUseCase
import com.shubham.famapp.ui.adapters.FamAdapter
import com.shubham.famapp.ui.adapters.FamClickListener
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.*
import javax.inject.Inject

@AndroidEntryPoint
class FamView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet?,
    defStyleAttr: Int = 0,
    defStyleRes: Int = 0
) : LinearLayout(
    context,
    attrs,
    defStyleAttr,
    defStyleRes
){

    private lateinit var famAdapter : FamAdapter
    private lateinit var binding: FamViewBinding
    private lateinit var listData: List<CardGroupModel>
    @Inject
    lateinit var cardDataUseCase: GetCardDataUseCase
    private val View.viewScope: CoroutineScope
        get() {
            val storedScope = getTag(R.string.view_coroutine_scope) as? CoroutineScope
            if (storedScope != null) {
                return storedScope
            }
            val newScope = ViewCoroutineScope()
            if (isAttachedToWindow) {
                addOnAttachStateChangeListener(newScope)
                setTag(R.string.view_coroutine_scope, newScope)
            } else {
                newScope.cancel()
            }
            return newScope
        }

    private class ViewCoroutineScope : CoroutineScope, OnAttachStateChangeListener {
        override val coroutineContext = SupervisorJob() + Dispatchers.Main

        override fun onViewAttachedToWindow(view: View) = Unit

        override fun onViewDetachedFromWindow(view: View) {
            coroutineContext.cancel()
            view.setTag(R.string.view_coroutine_scope, null)
        }
    }

    /**
     * This is called after onAttachedToWindow to inflate views and start fetching of data and handle swipe gesture
     */
    private fun initView(){
        val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        binding = DataBindingUtil.inflate(inflater,R.layout.fam_view, this, true)
        fetchAndInitData()
        handleSwipeReload()
    }

    private fun reloadData() {
        viewScope.launch {
            listData =cardDataUseCase.invoke(GetCardDataUseCase.Params("")).getOrNull()?.cardGroups!!
            dataReloaded(removedFromData(BLOCKED_CARD_LIST))
        }
    }

    /**
     * This function fetches data from internet and initializes recycler view and sends data to it
     */
    private fun fetchAndInitData(){
        viewScope.launch {
            listData =cardDataUseCase.invoke(GetCardDataUseCase.Params("")).getOrNull()?.cardGroups ?: emptyList()
            removeProgressBar()
            initRecyclerView()
            if(listData.isNullOrEmpty()){
                Toast.makeText(context, "Check your internet!!", Toast.LENGTH_LONG).show()
                return@launch
            }
            dataReloaded(removedFromData(BLOCKED_CARD_LIST))
        }
    }

    private fun removeProgressBar() {
        binding.progressPb.visibility = View.GONE
    }
    override fun onAttachedToWindow(){
        super.onAttachedToWindow()
        initView()
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


    private fun handleSwipeReload() {
        binding.swipeLayout.setOnRefreshListener {
            reloadData()
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
