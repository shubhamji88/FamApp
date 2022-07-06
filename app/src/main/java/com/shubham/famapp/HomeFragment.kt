package com.shubham.famapp

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.shubham.famapp.databinding.HomeFragmentBinding
import com.shubham.famapp.ui.adapters.FamAdapter
import com.shubham.famapp.ui.adapters.FamClickListener
import com.shubham.famapp.ui.customView.ReloadClickListener
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
        refreshData()
        initFamView()
        return binding.root
    }

    private fun initFamView(){
        viewModel.data.observe(viewLifecycleOwner) {
            if(it?.cardGroups != null) {
                binding.famView.initView(it.cardGroups, ReloadClickListener {
                    refreshData()
                })
            }
        }
    }

    private fun refreshData(){
        viewModel.call()
    }
}