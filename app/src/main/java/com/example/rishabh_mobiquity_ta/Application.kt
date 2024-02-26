package com.example.rishabh_mobiquity_ta

import android.app.Application
import android.util.Log
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class Application: Application() {

    override fun onCreate() {
        super.onCreate()
        Log.d(TAG, "Inside Application onCreate")
    }

    companion object{
        const val TAG = "ShoppingApp"
    }


}