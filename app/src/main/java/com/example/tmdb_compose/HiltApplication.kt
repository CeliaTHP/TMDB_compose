package com.example.tmdb_compose

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class HiltApplication : Application() {

    private lateinit var instance: HiltApplication;
    override fun onCreate() {
        super.onCreate()
        instance = this@HiltApplication
    }

    fun getInstance(): HiltApplication {
        return instance
    }
}