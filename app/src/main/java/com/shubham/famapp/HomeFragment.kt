package com.shubham.famapp

import android.os.Bundle
import android.util.AttributeSet
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.BindingAdapter
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.shubham.famapp.databinding.HomeFragmentBinding
import com.shubham.famapp.domain.model.FamCardModel
import com.shubham.famapp.ui.customView.FamView
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment: Fragment() {
    private val viewModel: MainViewModel by viewModels<MainViewModel>()
    private lateinit var binding: HomeFragmentBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding= DataBindingUtil.inflate(inflater,R.layout.home_fragment,container,false)
        viewModel.data.observe(viewLifecycleOwner) {
            if (it != null) {
                Log.d("Data revieved",it.toString())
                binding.famView.giveData(it)
            }
        }
        viewModel.call()
        return binding.root
    }
    @BindingAdapter("app:listData")
    fun FamView.giveData(data : FamCardModel){
        live.postValue(data)
    }
}