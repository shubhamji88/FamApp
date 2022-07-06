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
import com.shubham.famapp.databinding.FamViewBinding
import com.shubham.famapp.databinding.HomeFragmentBinding
import com.shubham.famapp.ui.adapters.FamAdapter
import com.shubham.famapp.ui.adapters.FamClickListener
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class HomeFragment: Fragment() {
    private val viewModel: MainViewModel by viewModels<MainViewModel>()
    private lateinit var binding: HomeFragmentBinding
    private lateinit var famBinding: FamViewBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding= DataBindingUtil.inflate(inflater,R.layout.home_fragment,container,false)
        famBinding = binding.famViewLayout
        setFamView()
        refreshData()
        return binding.root
    }

    private fun setFamView(){
        val famAdapter = FamAdapter (FamClickListener{url->
            openUrl(url)
        })
        famBinding.mainRv.adapter = famAdapter

        viewModel.data.observe(viewLifecycleOwner) {
            if(it?.cardGroups != null) {
                famAdapter.submitDesignList(it.cardGroups)
            }
        }
    }

    private fun refreshData(){
        viewModel.call()
    }

    private fun openUrl(url:String){
        startActivity(
            Intent(
                Intent.ACTION_VIEW,
                Uri.parse(url)
            )
        )
    }
}