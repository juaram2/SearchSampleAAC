package com.searchsampleaac

import android.app.Application
import android.util.Log
import dagger.hilt.android.HiltAndroidApp


@HiltAndroidApp
class SearchApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        Log.d("MainApplication", "Start!!!!")
    }
}