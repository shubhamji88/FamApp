package com.shubham.famapp

import android.app.Application
import com.shubham.famapp.data.SharedPrefManager
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class FamApplication: Application(){
    override fun onCreate() {
        super.onCreate()
        SharedPrefManager.createInstance(this)
    }
}